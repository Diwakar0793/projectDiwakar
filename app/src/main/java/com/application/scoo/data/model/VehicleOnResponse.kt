package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VehicleOnResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: String? = null

}