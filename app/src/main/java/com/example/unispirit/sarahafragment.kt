package com.example.unispirit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.unispirit.Adapter.SarahaAdapter
import com.example.unispirit.models.Messages
import com.example.unispirit.service.RetrofitApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

lateinit var mSharedPref: SharedPreferences

class sarahafragment : Fragment() {


    lateinit var recycleSarahAdapter: SarahaAdapter
    lateinit var messageRecyclerView: RecyclerView
    var mContext: Context?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.mContext=container!!.getContext()
        val rootView =  inflater.inflate(R.layout.fragment_sarahafragment, container, false)
        var send = rootView.findViewById<FloatingActionButton>(R.id.ajoutmessage)

        send .setOnClickListener{

           showdialogue()

        }
        val apiInterface = RetrofitApi.create()
        apiInterface.getAllMessage().enqueue(object: Callback<List<Messages>> {
            override fun onResponse(
                call: Call<List<Messages>>,response: Response<List<Messages>>
            ) {
                Log.i("yessss", response.body().toString())

                if(response.isSuccessful){
                    messageRecyclerView = rootView.findViewById(R.id.SarahaRecycleView)

                    recycleSarahAdapter = SarahaAdapter(response.body() as MutableList<Messages>)
                    messageRecyclerView.adapter = recycleSarahAdapter

                    messageRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,
                        false)
                    Log.i("yessss", response.body().toString())
                    //}
                } else {
                    Log.i("nooooo", response.body().toString())                }
            }
            override fun onFailure(call: Call<List<Messages>>, t: Throwable) {
                t.printStackTrace()
                println("OnFailure")
            }




        })



        return rootView
    }

    private fun showdialogue() {
        val dialog = BottomSheetDialog(mContext!!)
        dialog.setContentView(R.layout.sendmessage)


        mSharedPref = requireActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE)

        val published=dialog.findViewById<Button>(R.id.publish)
        val cancel=dialog.findViewById<Button>(R.id.cancel)
        val msgg=dialog.findViewById<TextInputEditText>(R.id.msgg)
        val layoutmsg=dialog.findViewById<TextInputLayout>(R.id.txtlayoutsendmessage)

        published?.setOnClickListener{ var msg = Messages()
            if(msgg?.text!!.isNotEmpty()) {
           msg.sarahaMessage=msgg!!.text.toString()
            val apiuser = RetrofitApi.create().send(msg)

            apiuser.enqueue(object: Callback<Messages> {
                override fun onResponse(call: Call<Messages>, response: Response<Messages>) {
                    if(response.isSuccessful ) {
                        println("###########################################")
                        println(response.body())

                    }
                    else
                    {
                        Toast.makeText(context, "message invalide ", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<Messages>, t: Throwable) {
                    Toast.makeText(context, "erreur server", Toast.LENGTH_LONG).show()
                }
            })
             dialog.dismiss()


        }
        }
        cancel?.setOnClickListener{
            dialog.dismiss()
        }


        dialog.show()    }


}


