package com.example.unispirit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationCompat.getVisibility
import com.example.unispirit.models.UserReset
import com.example.unispirit.models.UserResetResponse
import com.example.unispirit.service.RetrofitApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import me.sujanpoudel.wheelview.WheelView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Homeuserfragmenet : Fragment() {

    var mContext: Context?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.mContext=container!!.getContext()

        val rootview=inflater.inflate(R.layout.fragment_homeuserfragmenet, container, false)

        val wheelView=rootview.findViewById<WheelView>(R.id.wheel_view)

        wheelView.titles = listOf("Depressed", "Tough Day", "anixous", "stressed","desperate","sleepy")


        return rootview
        wheelView.setOnClickListener(){
            Toast.makeText(mContext, "ikhdem yakhra", Toast.LENGTH_LONG).show()
        }


    }





}