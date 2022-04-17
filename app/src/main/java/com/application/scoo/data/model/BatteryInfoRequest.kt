package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BatteryInfoRequest {
//    @SerializedName("api_key")
//    @Expose
//    var api_key: String? = null
//
//    @SerializedName("LoginID")
//    @Expose
//    var LoginID: String? = null
//
//    @SerializedName("imei")
//    @Expose
//    var imei: String? = null
//
//    @SerializedName("bat_status")
//    @Expose
//    var bat_status: String? = null


    @SerializedName("api_key")
    @Expose
    var apiKey: String? = null

    @SerializedName("LoginID")
    @Expose
    var loginID: String? = null

    @SerializedName("imei")
    @Expose
    var imei: String? = null

    @SerializedName("vehicle_number")
    @Expose
    var vehicleNumber: String? = null
}