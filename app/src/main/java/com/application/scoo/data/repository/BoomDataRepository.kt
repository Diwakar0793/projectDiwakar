package com.application.scoo.data.repository

import com.application.scoo.common.AppConstants
import com.application.scoo.data.model.*
import com.application.scoo.data.service.BoomDataService
import javax.inject.Inject

class BoomDataRepository @Inject constructor(private val mService: BoomDataService) {

    //Login
//    suspend fun login(request: LoginRequest) = mService.login(request)
    //New login(php)
    suspend fun login(api_key: String, password:String, username:String) =
        mService.login(api_key, password, username)

    suspend fun VehicleOnOff(api_key: String, LoginID: String, imei: String, veh_status: String) =
        mService.VehicleOnOff(api_key, LoginID, imei, veh_status)

    suspend fun TripOnOff(api_key: String, LoginID: String, imei: String, trip_status: String) =
        mService.TripOnOff(api_key, LoginID, imei, trip_status)

    suspend fun BatteryinfoData(api_key: String, LoginID: String, imei: String, vehicle_number: String) =
        mService.getBatteryData(api_key, LoginID, imei, vehicle_number)
    //otp verify
    suspend fun otpVerify(request: OTPRequest) = mService.otpVerify(request)

    //change password
    suspend fun changePassword(
        header: String,
        request: ChangePasswordRequest
    ): ChangePasswordResponse =
        mService.changePassword(header, request)


    //dashboard API with dummy data
    suspend fun getDashboardData(
        mHeader: String,
    ): DashboardResponse = mService.getDashboardData(mHeader)

    //Scoo
    suspend fun loginOTP(request: loginOTPRequest): loginOTPResponse = mService.loginOTP(AppConstants.appname,AppConstants.ContentType, request)
    suspend fun validateOTP(request: OTPRequest): OTPResponse = mService.validateOTP(AppConstants.appname,AppConstants.ContentType, request)
//    suspend fun vehicleNotification(mHeader: String, request: NotificationRequest): NotificationResponse = mService.vehicleNotification(mHeader, request)
    suspend fun vehicleNotification(mHeader: String, request: NotificationRequest): NotificationResponse = mService.vehicleNotification(mHeader,request)

    suspend fun getPaymentHistory(
        mHeader: String, userid: String, franciseVehicleId: String
    ): PaymentHistoryResponse = mService.getPaymentHistory(mHeader, userid, franciseVehicleId)
}