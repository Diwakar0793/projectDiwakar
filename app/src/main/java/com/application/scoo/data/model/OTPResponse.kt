package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class OTPResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("data")
    @Expose
    var data: OTPResponseDetails? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}

class OTPResponseDetails {
    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("users")
    @Expose
    var users: List<UserDetails>? = null
}

class UserDetails {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("user_name")
    @Expose
    var userName: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("mobile_no")
    @Expose
    var mobileNo: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("role_id")
    @Expose
    var roleId: Int? = null

    @SerializedName("email_id")
    @Expose
    var emailId: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("created_date")
    @Expose
    var createdDate: Any? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: Any? = null

    @SerializedName("updated_date")
    @Expose
    var updatedDate: Any? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("franchise_vehicle_id")
    @Expose
    var franchiseVehicleId: String? = null

    @SerializedName("assigned_date")
    @Expose
    var assignedDate: String? = null

    @SerializedName("form_ref_no")
    @Expose
    var formRefNo: String? = null

    @SerializedName("vehicle_type")
    @Expose
    var vehicleType: String? = null

    @SerializedName("plan_id")
    @Expose
    var planId: String? = null

    @SerializedName("plan_start_date")
    @Expose
    var planStartDate: String? = null

    @SerializedName("plan_end_date")
    @Expose
    var planEndDate: String? = null

    @SerializedName("pre_rental_condition")
    @Expose
    var preRentalCondition: String? = null

    @SerializedName("post_return_condition")
    @Expose
    var postReturnCondition: Any? = null

    @SerializedName("date_of_registration")
    @Expose
    var dateOfRegistration: String? = null

    @SerializedName("vehicle_id")
    @Expose
    var vehicleId: String? = null

    @SerializedName("make")
    @Expose
    var make: String? = null

    @SerializedName("model")
    @Expose
    var model: String? = null

    @SerializedName("registration_no")
    @Expose
    var registrationNo: String? = null

    @SerializedName("chassis_no")
    @Expose
    var chassisNo: String? = null

    @SerializedName("motor_no")
    @Expose
    var motorNo: String? = null

    @SerializedName("charger_no")
    @Expose
    var chargerNo: String? = null

    @SerializedName("battery_no")
    @Expose
    var batteryNo: String? = null

    @SerializedName("battery_voltage")
    @Expose
    var batteryVoltage: String? = null

    @SerializedName("device_no")
    @Expose
    var deviceNo: String? = null

    @SerializedName("sim_no")
    @Expose
    var simNo: String? = null

    @SerializedName("plan_name")
    @Expose
    var planName: String? = null

    @SerializedName("plan_description")
    @Expose
    var planDescription: String? = null

    @SerializedName("plan_frequency")
    @Expose
    var planFrequency: String? = null

    @SerializedName("plan_amount")
    @Expose
    var planAmount: Int? = null

    @SerializedName("plan_startdate")
    @Expose
    var planStartdate: String? = null

    @SerializedName("plan_enddate")
    @Expose
    var planEnddate: String? = null
}
