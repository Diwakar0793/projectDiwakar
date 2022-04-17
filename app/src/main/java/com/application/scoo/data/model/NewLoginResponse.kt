package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class NewLoginResponse {
    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: NewLoginResponseData? = null
}

class NewLoginResponseData {
    @SerializedName("LoginID")
    @Expose
     var loginID: String? = null

    @SerializedName("UserName")
    @Expose
     var userName: String? = null

}
