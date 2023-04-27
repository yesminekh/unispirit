package com.example.unispirit.service

import com.example.unispirit.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit

import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface RetrofitApi {

    @POST("/user/login")
    fun userLogin(
        @Body user: UserResetPassword,
    ):Call<User>

    @POST("user/singup")
    fun userSingup(
        @Body user:User,
    ):Call<User>

    @POST("user/forgotPassword")
    fun sendResetCode(
        @Body user: UserReset
    ):Call<UserResetResponse>

    @PUT("user/editPassword")
    fun changePasswordReset(
        @Body user:UserResetPassword):Call<User>


    @POST("message/sendMessage")
    fun send(
        @Body user: Messages
    ):Call<Messages>

    @GET("message/getall")
    fun getAllMessage()
    :Call<List<Messages>>

    companion object {

        var BASE_URL = "http://192.168.1.13:3000"
        //var BASE_URL = "https://unispirrit.herokuapp.com"
        // baad badl add ip hathy bmtaak

        fun create() : RetrofitApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.13:3000/")
                .build()

            return retrofit.create(RetrofitApi::class.java)
        }
    }
}