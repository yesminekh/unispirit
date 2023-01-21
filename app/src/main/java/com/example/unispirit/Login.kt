package com.example.unispirit

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.unispirit.models.User
import com.example.unispirit.models.UserResetPassword
import com.example.unispirit.service.RetrofitApi
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val IS_REMEMBRED = "IS_REMEMBRED"
const val PREF_NAME = "LOGIN_PREF_LOL"
const val LOGIN = "LOGIN"
const val PASSWORD = "PASSWORD"

class Login : AppCompatActivity() {
    lateinit var mSharedPref: SharedPreferences
    private var email:EditText?=null
    private  var password:EditText?=null
    private var forgetPassword:TextView?=null
    private  var singUp:TextView?=null
    private var login:Button?=null

    lateinit var rm: CheckBox
    lateinit var role: CheckBox

    private var txtlayoutemail: TextInputLayout?=null
    private var txtlayoutpassword: TextInputLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singin)
        title="welcome"
        email=findViewById(R.id.txtemail)
        password=findViewById(R.id.txtpassword)
        txtlayoutemail=findViewById(R.id.txtLayoutemail)
        txtlayoutpassword=findViewById(R.id.txtLayoutpassword)
        forgetPassword=findViewById(R.id.txtfpswd)
        singUp=findViewById(R.id.singup)
        rm=findViewById(R.id.cbrmbr)
        login=findViewById(R.id.btnlogin)
        mSharedPref = getSharedPreferences("UserPref", MODE_PRIVATE)

        if (mSharedPref.getBoolean(IS_REMEMBRED, false)){
            val mainIntent = Intent(this, Home::class.java)
            startActivity(mainIntent)
        }


        login!!.setOnClickListener { var user = UserResetPassword()
            user.email = email?.text.toString()
            user.password = password?.text.toString()
            val apiuser = RetrofitApi.create().userLogin(user)
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

                                if(rm.isChecked()){
                                    putBoolean("IS_REMEMBRED", true)

                                }
                                println("###########################################")
                                println(response.body())
                                println("###########################################")
                                putString("tokenUser", response.body()?.token.toString())
                            }.apply()

                            if(response.body()?.role.equals("NormalUser"))
                            {
                                println(response.body()?.role)

                                val intent = Intent(applicationContext, Home::class.java)
                                startActivity(intent)
                            }
                            else {
                                val intent = Intent(applicationContext, Homepsy::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)

                            }


                        }

                        else {

                            Toast.makeText(applicationContext, "Failed to Login , email or password incorrect ", Toast.LENGTH_LONG).show()
                        } }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        println("faillure")

                        Toast.makeText(applicationContext, "erreur server", Toast.LENGTH_LONG).show()
                    }
                }) } }



        singUp!!.setOnClickListener{

            val mainIntent = Intent(this, SingUp::class.java)
            startActivity(mainIntent)
        }
        forgetPassword!!.setOnClickListener {

            val mainIntent = Intent(this, ForgetPassword::class.java)
            startActivity(mainIntent)
        }
    }

    private fun validate(): Boolean {
        if(email?.text!!.isEmpty())
        {
            txtlayoutemail!!.error="Please enter Your Email"
            return false

        }
        if(password?.text!!.isEmpty())
        {
            txtlayoutpassword!!.error="Please enter Your Password"
            return false

        }
        return true
    }
}