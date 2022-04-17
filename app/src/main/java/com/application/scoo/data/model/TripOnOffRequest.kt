package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TripOnOffRequest {
    @SerializedName("api_key")
    @Expose
    var api_key: String? = null

    @SerializedName("LoginID")
    @Expose
    var LoginID: String? = null

    @SerializedName("imei")
    @Expose
    var imei: String? = null

    @SerializedName("trip_status")
    @Expose
    var trip_status: String? = null
}