package com.example.unispirit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.example.unispirit.models.User
import com.example.unispirit.models.UserResetPassword
import com.example.unispirit.service.RetrofitApi
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private var layoutold: TextInputLayout?=null
    private var layoutnew: TextInputLayout?=null
    private var layoutconfi: TextInputLayout?=null
    private var old: EditText?=null
    private var password: EditText?=null
    private var confirme: EditText?=null


    lateinit var gone: LinearLayout
    lateinit var gone01: LinearLayout
    lateinit var gone02: LinearLayout
    lateinit var show: CardView
    lateinit var profilee:CardView
    lateinit var theme: CardView
    lateinit var dark: Switch

    private var update: Button?=null
    private var update01: Button?=null
    lateinit var  mSharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSharedPref = this.requireActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE)

        val rootview=inflater.inflate(R.layout.fragment_profile, container, false)
        layoutold=rootview.findViewById(R.id.txtLayoutpassword)
        layoutnew=rootview.findViewById(R.id.txtLayoutnewpassword)
        layoutconfi=rootview.findViewById(R.id.txtLayoutcnfnewpassword)
        old=rootview.findViewById(R.id.txtpassword)
        password=rootview.findViewById(R.id.txtnewpassword)
        confirme=rootview.findViewById(R.id.txtcnfnewpassword)
        update=rootview.findViewById(R.id.btnupdate)
        update=rootview.findViewById(R.id.btnupdate01)
        gone=rootview.findViewById(R.id.gone)
        gone01=rootview.findViewById(R.id.gone1)
        gone02=rootview.findViewById(R.id.gone02)
        show=rootview.findViewById(R.id.off)
        profilee=rootview.findViewById(R.id.profile)
        theme=rootview.findViewById(R.id.theme)
        dark=rootview.findViewById(R.id.theme01)
        var email=rootview.findViewById<TextView>(R.id.editemail)
        var nom=rootview.findViewById<TextView>(R.id.editname)
        var phone=rootview.findViewById<TextView>(R.id.editphone)
        var genders=rootview.findViewById<TextView>(R.id.editgender)
        var Editaddresse=rootview.findViewById<TextView>(R.id.editadresse)

        val emaill: String = mSharedPref.getString("LOGIN","yesmine").toString()
        val fullname: String = mSharedPref.getString("FULLNAME","yesmine.khayati").toString()
        val addresseshared: String = mSharedPref.getString("ADDRESS","nabeul").toString()
        val phonee: String = mSharedPref.getString("PHONE","29385320").toString()
        val gender: String = mSharedPref.getString("GENDER","femme").toString()
        email.text=emaill;
        nom.text=fullname;
        phone.text=phonee;
        genders.text=gender;
        Editaddresse.text=addresseshared;
         println("654646"+ addresseshared)

        dark.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(dark.isChecked){
                AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO)
            } }
        show.setOnClickListener {
            if(gone.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(show);
                gone.setVisibility(View.GONE);
            }
            else {
                TransitionManager.beginDelayedTransition(show);
                gone.setVisibility(View.VISIBLE);
            }
        }
        theme.setOnClickListener {
            if(gone01.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(theme);
                gone01.setVisibility(View.GONE);
            }
            else {
                TransitionManager.beginDelayedTransition(theme);
                gone01.setVisibility(View.VISIBLE);
            }
        }
        profilee.setOnClickListener {
            if(gone02.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(profilee);
                gone02.setVisibility(View.GONE);
            }
            else {
                TransitionManager.beginDelayedTransition(profilee);
                gone02.setVisibility(View.VISIBLE);
            }
        }

        update!!.setOnClickListener{
            var passRes = UserResetPassword()
            if(password?.text.toString() == confirme?.text.toString()){
                passRes.password = confirme?.text.toString()
                passRes. email =  mSharedPref.getString("LOGIN","yesmine").toString()

                val apiuser =
                    RetrofitApi.create().changePasswordReset(passRes)
                apiuser.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(old?.text.toString()==response.body()?.password){
                            if (response.isSuccessful) {
                                Toast.makeText(context,"done",Toast.LENGTH_SHORT)
                                println("jawk bahy ")

                            } else {

                                Log.i("RETROFIT_API_RESPONSE", response.toString())
                                Log.i("login response", response.body().toString())
                                Toast.makeText(context,"aandkmochkla",Toast.LENGTH_SHORT)

                            }}
                        else {
                            Toast.makeText(context, "password incorrect", Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                        println("aaaaaa")                    }
                })
            }else {
                println("el passwords moch kifkif")
            }

        }

        return  rootview
    }


}