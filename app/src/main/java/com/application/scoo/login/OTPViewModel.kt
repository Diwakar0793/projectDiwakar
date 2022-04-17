package com.application.scoo.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.scoo.common.*
import com.application.scoo.data.model.OTPRequest
import com.application.scoo.data.model.OTPResponse
import com.application.scoo.data.repository.BoomDataRepository
import com.application.scoo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class OTPViewModel @Inject constructor(private val dataRepository: BoomDataRepository) :
    BaseViewModel() {

    private var motpVerifyData: MutableLiveData<ViewState<OTPResponse>> =
        MutableLiveData()

    fun getOtpVerify(): MutableLiveData<ViewState<OTPResponse>> {
        return motpVerifyData
    }

    private fun onError(throwable: Throwable) {
        motpVerifyData.value = NetworkError(throwable.message)
    }

    fun otpVerify(request: OTPRequest) {
        motpVerifyData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Create User Registration
            val otpData = dataRepository.validateOTP(request)
            if (otpData.code == AppConstants.Success) {
                motpVerifyData.value = Success(otpData)
            } else {
                motpVerifyData.value = NetworkError(otpData.message)
            }
        }
    }

}
