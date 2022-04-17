package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BatteryInfoResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<BatteryInfoResponseDetails>? = null
}

class BatteryInfoResponseDetails {

    @SerializedName("info_id")
    @Expose
    var infoId: String? = null

    @SerializedName("entry_date")
    @Expose
    var entryDate: String? = null

    @SerializedName("entry_time")
    @Expose
    var entryTime: String? = null

    @SerializedName("motor_current")
    @Expose
    var motorCurrent: String? = null

    @SerializedName("motor_speed")
    @Expose
    var motorSpeed: String? = null

    @SerializedName("odometer_reading")
    @Expose
    var odometerReading: String? = null

    @SerializedName("controller_voltage")
    @Expose
    var controllerVoltage: String? = null

    @SerializedName("throttle_signal")
    @Expose
    var throttleSignal: String? = null

    @SerializedName("motor_temperature")
    @Expose
    var motorTemperature: String? = null

    @SerializedName("soc")
    @Expose
    var soc: String? = null

    @SerializedName("sop")
    @Expose
    var sop: String? = null

    @SerializedName("battery_current")
    @Expose
    var batteryCurrent: String? = null

    @SerializedName("battery_voltage")
    @Expose
    var batteryVoltage: String? = null

    @SerializedName("soh")
    @Expose
    var soh: String? = null

    @SerializedName("battery_temperature")
    @Expose
    var batteryTemperature: String? = null
}
