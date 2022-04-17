package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationRequest {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("vehicle_id")
    @Expose
    var vehicleId: Int? = null
}
