package com.example.unispirit.models

data class User(
    var token: String? = null,
    var _id: String? = null,
    var adresse: String? = null,
    var role : String?=null,
    var email: String? = null,
    var fullName: String? = null,
    var password: String? = null,
    var phone: String? = null,
    var createdAt:String?=null,
    var updatedAt: String?= null,
    var DatedeNaissance: String? = null,
    var gender:String?=null,

)
data class UserResetPassword (
    var email: String? = null,
    var password: String? = null

)

data class UserResetResponse (
    val msgg: String? = null

)

data class UserReset (
    var email: String? = null,
    var resetCode : String?=null

)
data class Messages(
    var sarahaMessage:String?=null,
    var _id: String? = null,
    var createdAt:String?= null,
)
