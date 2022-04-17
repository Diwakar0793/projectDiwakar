package com.application.scoo.ui.home.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.scoo.AppBase
import com.application.scoo.common.*
import com.application.scoo.data.model.NotificationRequest
import com.application.scoo.data.model.NotificationResponse
import com.application.scoo.data.repository.BoomDataRepository
import com.application.scoo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(private val dataRepository: BoomDataRepository) :
    BaseViewModel() {

    private var mvNotificationData: MutableLiveData<ViewState<NotificationResponse>> =
        MutableLiveData()

    fun getvehNotification(): MutableLiveData<ViewState<NotificationResponse>> {
        return mvNotificationData
    }

    private fun onError(throwable: Throwable) {
        mvNotificationData.value = NetworkError(throwable.message)
    }

    fun vehicleNotification(request: NotificationRequest) {
        mvNotificationData.value = Loading
        val header = "Bearer " + AppBase.sharedPreference?.getValueString(AppConstants.token).toString()
        Log.i("header",header)
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Create User Registration
            val VehNotificationData = dataRepository.vehicleNotification(header, request)
            if (VehNotificationData.code == AppConstants.Success) {
                mvNotificationData.value = Success(VehNotificationData)
            } else {
                mvNotificationData.value = NetworkError(VehNotificationData.message)
            }
        }
    }
}