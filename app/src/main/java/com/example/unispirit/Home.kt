package com.example.unispirit

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportFragmentManager.beginTransaction().replace(R.id.frame, Homeuserfragmenet()).commit()


        val homeBtn = findViewById<ImageView>(R.id.home_icon)
        val psyBtn = findViewById<ImageView>(R.id.psy_icon)
        val profileBtn = findViewById<ImageView>(R.id.profile_icon)



        val toolbar  =  findViewById<Toolbar>(R.id.toolbar01) ;
        setSupportActionBar(toolbar)

        homeBtn.setOnClickListener(clickListener)
        psyBtn.setOnClickListener(clickListener)
        profileBtn.setOnClickListener(clickListener)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lateinit var mSharedPref: SharedPreferences
        when(item.itemId){

            R.id.logout ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Logout")
                builder.setMessage("logout")
                builder.setPositiveButton("Yes"){ dialogInterface, which ->
                    getSharedPreferences("UserPref", MODE_PRIVATE).edit().clear().apply()
                    val mainIntent = Intent(this, Login::class.java)
                    startActivity(mainIntent)
                    finish()
                }
                builder.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                builder.create().show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private val clickListener : View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.home_icon -> {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, Homeuserfragmenet()).commit()
            }
            R.id.psy_icon -> {

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, sarahafragment()).commit()
            }

            R.id.profile_icon -> {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, ProfileFragment()).commit()
            }



        }
    }
    }
