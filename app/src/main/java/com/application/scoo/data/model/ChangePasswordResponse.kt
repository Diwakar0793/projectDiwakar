package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ChangePasswordResponse {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Boolean? = null
}