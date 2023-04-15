package com.example.unispirit.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.unispirit.R
import com.example.unispirit.models.Messages
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class SarahaAdapter(val reservationList: MutableList<Messages>) :  RecyclerView.Adapter<SarahaAdapter.viewHolder>() {


    lateinit var mSharedPref: SharedPreferences
    var mContext: Context?=null

    class viewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val id1 = itemView.findViewById<TextView>(R.id.id)
        val date = itemView.findViewById<TextView>(R.id.date)
        val message = itemView.findViewById<TextView>(R.id.message)}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.sarahamodel, parent, false)

        return viewHolder (rootView)    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        val id = reservationList[position]._id?.substring(0,6)
        val messages = reservationList[position].sarahaMessage
        val dateget = reservationList[position].createdAt!!.substring(0,11)

        holder.id1.text = id
        holder.message.text = messages
        holder.date.text=dateget

    }

    override fun getItemCount(): Int {
        return reservationList.size
    }
}
