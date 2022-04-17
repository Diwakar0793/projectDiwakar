package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DashboardResponse {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null
}

class  Data{
    @SerializedName("battery_left")
    @Expose
    var batteryLeft: String? = null

    @SerializedName("range_left")
    @Expose
    var rangeLeft: String? = null

    @SerializedName("variant_name")
    @Expose
    var variantName: String? = null
}

