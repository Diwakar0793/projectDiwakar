package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OTPRequest {
        @SerializedName("mobile_no")
        @Expose
        var mobileNo: String? = null

        @SerializedName("otp")
        @Expose
        var otp: Int? = null

        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("type_id")
        @Expose
        var typeId: Int? = null

}


