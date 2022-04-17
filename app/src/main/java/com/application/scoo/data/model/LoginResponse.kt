package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("data")
    @Expose
    var data: LoginDetails? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}

class LoginDetails {
    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("thisUser")
    @Expose
    var thisUser: ThisUser? = null
}

class ThisUser {
    @SerializedName("user_id")
    @Expose
    var userId: String? = null

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
}