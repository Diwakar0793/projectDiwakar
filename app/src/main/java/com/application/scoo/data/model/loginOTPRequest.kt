package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class loginOTPRequest {
    @SerializedName("mobile_no")
    @Expose
    var mobileNo: String? = null

//    fun getMobileNo(): String? {
//        return mobileNo
//    }
//
//    fun setMobileNo(mobileNo: String?) {
//        this.mobileNo = mobileNo
//    }
}
