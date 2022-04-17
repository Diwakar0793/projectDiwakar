package com.application.scoo.data.service

import com.application.scoo.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface BoomDataService {
//    //Old Login
//    @POST("api/auth/login")
//    suspend fun login(@Body request: LoginRequest): LoginResponse

    //New login(php)
    @FormUrlEncoded
    @POST("http://jtrack.in/ebike_app_new/login.php")
    suspend fun login(
        @Field("api_key") api_key: String,
        @Field("password") password: String,
        @Field("username") username: String
    ): NewLoginResponse

    @FormUrlEncoded
    @POST("http://jtrack.in/ebike_app_new/vehicle_on_off.php")
    suspend fun VehicleOnOff(
        @Field("api_key") api_key: String,
        @Field("LoginID") LoginID: String,
        @Field("imei") imei: String,
        @Field("veh_status") veh_status: String
    ): VehicleOnResponse

    @FormUrlEncoded
    @POST("http://jtrack.in/ebike_app_new/trip_on_off.php\n")
    suspend fun TripOnOff(
        @Field("api_key") api_key: String,
        @Field("LoginID") LoginID: String,
        @Field("imei") imei: String,
        @Field("trip_status") trip_status: String
    ): TripOnOffResponse

    @FormUrlEncoded
    @POST("http://jtrack.in/ebike_app_new/get_motor_battery_can.php")
    suspend fun getBatteryData(
        @Field("api_key") api_key: String,
        @Field("LoginID") LoginID: String,
        @Field("imei") imei: String,
        @Field("vehicle_number") vehicle_number: String
    ): BatteryInfoResponse



    @POST("api/auth/verifyConsumerOtp")
    suspend fun otpVerify(@Body request: OTPRequest): OTPResponse

    @POST("api/consumer/changePassword")
    suspend fun changePassword(
        @Header("authorization") header: String,
        @Body request: ChangePasswordRequest
    ): ChangePasswordResponse

    @GET("api/consumer/dashboard")
    suspend fun getDashboardData(
        @Header("authorization") mHeader: String,
    ): DashboardResponse

    @POST("account/MobileLogin")
    suspend fun loginUser(@Header("appname") appName: String, @Body request: LoginRequest): LoginResponse

    // Scoo
    @POST("account/sendOTP")
    suspend fun loginOTP(@Header("appname") appName: String,@Header("ContentType") ContentType: String, @Body request: loginOTPRequest): loginOTPResponse

    @POST("account/validateOTP")
    suspend fun validateOTP(@Header("appname") appName: String, @Header("ContentType") ContentType: String , @Body request: OTPRequest): OTPResponse


    @POST("settings/vehicalNotificationByVehicleUserId")
    suspend fun vehicleNotification(@Header("authorization") mHeader: String, @Body request: NotificationRequest): NotificationResponse
//
//    @POST("settings/vehicalNotificationByVehicleUserId")
//    suspend fun vehicleNotification(@Header ("authorisation") mHeader: String, @Body request: NotificationRequest): NotificationResponse
//

    @GET("settings/selectPaymentHistory/{user_id}/{francise_vehicle_id}")
    suspend fun getPaymentHistory(
        @Header("authorization") mHeader: String,
        @Path("user_id") userId: String,
        @Path("francise_vehicle_id") franciseVehicleId: String
    ): PaymentHistoryResponse


}