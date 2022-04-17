package com.application.scoo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.scoo.AppBase
import com.application.scoo.common.*
import com.application.scoo.data.model.*
import com.application.scoo.data.repository.BoomDataRepository
import com.application.scoo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val dataRepository: BoomDataRepository) :
    BaseViewModel() {

    private var mDashboardData: MutableLiveData<ViewState<DashboardResponse>> =
        MutableLiveData()

    private var mVehicleData: MutableLiveData<ViewState<VehicleOnResponse>> =
        MutableLiveData()

    private var mTripData: MutableLiveData<ViewState<TripOnOffResponse>> =
        MutableLiveData()

    private var mBattData: MutableLiveData<ViewState<BatteryInfoResponse>> =
        MutableLiveData()

    private fun onError(throwable: Throwable) {
        mDashboardData.value = NetworkError(throwable.message)
    }

    private fun onErrorVehoNof(throwable: Throwable) {
        mVehicleData.value = NetworkError(throwable.message)
    }

    private fun onErrorTripOnOff(throwable: Throwable) {
        mTripData.value = NetworkError(throwable.message)
    }

    private fun onErrorBatt(throwable: Throwable) {
        mBattData.value = NetworkError(throwable.message)
    }

    fun callDashboardData(): MutableLiveData<ViewState<DashboardResponse>> {
        return mDashboardData
    }

    fun callVehicleOnOff(): MutableLiveData<ViewState<VehicleOnResponse>> {
        return mVehicleData
    }

    fun callTripOnOff(): MutableLiveData<ViewState<TripOnOffResponse>> {
        return mTripData
    }

    fun callBattData(): MutableLiveData<ViewState<BatteryInfoResponse>> {
        return mBattData
    }

    fun getDashboardData() {
        mDashboardData.value = Loading
        val header = AppBase.sharedPreference?.getValueString(AppConstants.mAuth).toString()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            val dashboardDetail = dataRepository.getDashboardData(
                mHeader = header
            )
            if (dashboardDetail.message.equals("Success")) {
                mDashboardData.value = Success(dashboardDetail)
            } else {
                mDashboardData.value = NetworkError(dashboardDetail.message)
            }
        }
    }

    fun VehicleOnOff(request: VehicleOnRequest) {
        mVehicleData.value = Loading
        request.LoginID = AppBase.sharedPreference!!.getValueString(AppConstants.loginID)
        // val header = AppBase.sharedPreference?.getValueString(AppConstants.loginID).toString()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onErrorVehoNof(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            val vehonoff = dataRepository.VehicleOnOff(
                request.api_key!!, request.LoginID!!,
                request.imei!!, request.veh_status!!
            )
            if (vehonoff.data?.contains("Vehicle") == true) {
                mVehicleData.value = Success(vehonoff)
            } else {
                mVehicleData.value = NetworkError(vehonoff.message)
            }
        }
    }

    fun TripOnOff(request: TripOnOffRequest) {
        mTripData.value = Loading
        request.LoginID = AppBase.sharedPreference!!.getValueString(AppConstants.loginID)
        // val header = AppBase.sharedPreference?.getValueString(AppConstants.loginID).toString()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onErrorTripOnOff(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            val tripOnOff = dataRepository.TripOnOff(
                request.api_key!!, request.LoginID!!,
                request.imei!!, request.trip_status!!
            )
            if (tripOnOff.data?.contains("Trip") == true) {

                mTripData.value = Success(tripOnOff)

            } else {
                mTripData.value = NetworkError(tripOnOff.message)
            }
        }
    }

    fun BatteryInfoData(request: BatteryInfoRequest) {
        mBattData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onErrorBatt(exception)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            val battData = dataRepository.BatteryinfoData(
                request.apiKey!!, request.loginID!!,
                request.imei!!, request.vehicleNumber!!
            )
            if (battData.message.equals("Success")) {
                mBattData.value = Success(battData)
            } else {
                mBattData.value = NetworkError(battData.message)
            }
        }
    }
}
