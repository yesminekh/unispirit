package com.example.unispirit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.unispirit.models.User
import com.example.unispirit.models.UserResetPassword
import com.example.unispirit.service.RetrofitApi
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingUp : AppCompatActivity() {
    lateinit var  mSharedPref: SharedPreferences
    private var fullname : EditText?=null
    private var email : EditText?=null
    private var Datedenaissance :EditText?=null
    private var  adresse: EditText?=null
    private var phone :EditText?=null
    private var password : EditText?=null
    lateinit var femme : CheckBox
    lateinit var homme : CheckBox
    lateinit var psy : CheckBox
    private var singin:TextView?=null

    private var Layoutemail:TextInputLayout?=null
    private var layoutfullname:TextInputLayout?=null
    private var layoutdatedenaissance:TextInputLayout?=null
    private var layoutadresse : TextInputLayout?=null
    private var layoutphone:TextInputLayout?=null
    private var layoutpassword : TextInputLayout?=null
    private var btnext:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        mSharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        fullname=findViewById(R.id.txtFullName)
        email=findViewById(R.id.txtEmail)
        phone=findViewById(R.id.txtPhone)
        password=findViewById(R.id.txtPassword)
        adresse=findViewById(R.id.txtadresse)
        layoutdatedenaissance=findViewById(R.id.txtLayoutAge)
        Datedenaissance=findViewById(R.id.txtAge)
        layoutfullname=findViewById(R.id.txtLayoutFullName)
        Layoutemail=findViewById(R.id.txtLayoutemail)
        layoutphone=findViewById(R.id.txtLayoutPhone)
        layoutpassword=findViewById(R.id.txtLayoutpassword )
        layoutadresse=findViewById(R.id.txtlayoutadresse)
        psy=findViewById(R.id.role)
        femme=findViewById(R.id.sexef)
        homme=findViewById(R.id.sexeh)
        btnext=findViewById(R.id.btnNext1)
        singin=findViewById(R.id.singin)

        singin!!.setOnClickListener {

            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)

        }




        btnext!!.setOnClickListener {var user = User()
            var sexe="null"
            if(femme !!.isChecked){
                sexe="femme"
            }
            if(homme!!.isChecked){
                sexe="homme"
            }
            var rolee="null"
            if(psy!!.isChecked){
                rolee="psy"}
            else{
                rolee="NormaleUser"}


            user.email = email?.text.toString()
            user.password = password?.text.toString()
            user.fullName = fullname?.text.toString()
            user.DatedeNaissance = Datedenaissance?.text.toString()
            user.phone = phone?.text.toString()
            user.adresse = adresse?.text.toString()
            user.role=rolee.toString()
            user.gender=sexe.toString()
            val apiuser = RetrofitApi.create().userSingup(user)
            if(validate()){
                apiuser.enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful ){
                            println("assslema !!!!")
                            //print(response.body()?.photo.toString())

                            mSharedPref.edit().apply {
                                putString("ID",response.body()?._id.toString())
                                putString("LOGIN", response.body()?.email.toString())
                                putString("PASSWORD", response.body()?.password.toString())
                                putString("FULLNAME", response.body()?.fullName.toString())
                                putString("ADDRESS", response.body()?.adresse.toString())
                                putString("PHONE", response.body()?.phone.toString())
                                putString("ROLE", response.body()?.role.toString())
                                println(response.body())

                                println("###########################################")
                                println(response.body())
                                println("###########################################")
                                putString("tokenUser", response.body()?.token.toString())
                            }.apply()

                            val intent = Intent(applicationContext, Login::class.java)
                            startActivity(intent)




                        }

                        else {

                            Toast.makeText(applicationContext, "Failed to Singup , please try later ", Toast.LENGTH_LONG).show()
                        } }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        println("faillure")

                        Toast.makeText(applicationContext, "erreur server", Toast.LENGTH_LONG).show()
                    }
                }) } }

    }











    private fun validate(): Boolean {

        if(fullname?.text!!.isEmpty()){
            layoutfullname!!.error="Must not be empty"
            return false}
        if(email?.text!!.isEmpty())
        { Layoutemail!!.error="Please enter Your Email"
            return false
        }
        if(phone?.text!!.isEmpty()){
            layoutphone!!.error="Must not be empty"
            return false}

        if(phone?.text!!.isEmpty()){
            layoutphone!!.error="Must not be empty"
            return false}
        if(Datedenaissance?.text!!.isEmpty()){
            layoutdatedenaissance!!.error="Must not be empty"
            return false}
        if(password?.text!!.isEmpty()){
            layoutpassword!!.error="Must not be empty"
            return false}
        if(adresse?.text!!.isEmpty()){
            layoutpassword!!.error="Must not be empty"
            return false}

        return true
    }
}
