package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePasswordRequest {
        @SerializedName("user_id")
        @Expose
        var userId: String? = null

        @SerializedName("current_password")
        @Expose
        var currentPassword: String? = null

        @SerializedName("new_password")
        @Expose
        var newPassword: String? = null
}


