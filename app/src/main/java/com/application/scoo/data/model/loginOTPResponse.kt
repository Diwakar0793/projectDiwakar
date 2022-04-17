package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class loginOTPResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("data")
    @Expose
    var data: loginOTPResponseDetails? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}

class loginOTPResponseDetails {
    @SerializedName("contact")
    @Expose
    var contact: String? = null

    @SerializedName("message")
    @Expose
   var message: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

}
