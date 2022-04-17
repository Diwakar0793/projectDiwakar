package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest {
        @SerializedName("mobile_no")
        @Expose
        var mobileno: String? = null

        @SerializedName("password")
        @Expose
        var password: String? = null

        @SerializedName("role_id")
        @Expose
        var roleId: Number? = null
}




