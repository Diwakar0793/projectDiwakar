package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewLoginRequest {

    @SerializedName("api_key")
    @Expose
    var apiKey: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}
//
//class LoginRequest {
//    @SerializedName("email")
//    @Expose
//    var email: String? = null
//
//    @SerializedName("password")
//    @Expose
//    var password: String? = null
//}
