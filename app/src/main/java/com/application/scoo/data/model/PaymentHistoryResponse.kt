package com.application.scoo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentHistoryResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}

class Datum {
    @SerializedName("francise_vehicle_id")
    @Expose
    var franciseVehicleId: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("razorpay_order_id")
    @Expose
    var razorpayOrderId: String? = null

    @SerializedName("paid_amount")
    @Expose
    var paidAmount: Int? = null

    @SerializedName("payment_id")
    @Expose
    var paymentId: String? = null

    @SerializedName("paid_date")
    @Expose
    var paidDate: String? = null

    @SerializedName("paymentmethos")
    @Expose
    var paymentmethos: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}