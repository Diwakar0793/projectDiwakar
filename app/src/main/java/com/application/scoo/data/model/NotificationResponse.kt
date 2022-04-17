package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("data")
    @Expose
    var data: List<NotificationResponseDetails>? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}

class NotificationResponseDetails {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("vehicle_id")
    @Expose
    var vehicleId: String? = null

    @SerializedName("antitheft")
    @Expose
    var antitheft: Boolean? = null

    @SerializedName("ignition")
    @Expose
    var ignition: Boolean? = null

    @SerializedName("batterylow")
    @Expose
    var batterylow: Boolean? = null

    @SerializedName("geofencealert")
    @Expose
    var geofencealert: Boolean? = null

    @SerializedName("towing")
    @Expose
    var towing: Boolean? = null

    @SerializedName("lock")
    @Expose
    var lock: Boolean? = null

    @SerializedName("unlock")
    @Expose
    var unlock: Boolean? = null

    @SerializedName("geo_points")
    @Expose
    var geoPoints: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("created_date")
    @Expose
    var createdDate: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

    @SerializedName("updated_date")
    @Expose
    var updatedDate: String? = null
}
