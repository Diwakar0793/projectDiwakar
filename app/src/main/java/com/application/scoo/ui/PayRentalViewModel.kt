package com.application.scoo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.scoo.AppBase
import com.application.scoo.common.*
import com.application.scoo.data.model.OTPRequest
import com.application.scoo.data.model.OTPResponse
import com.application.scoo.data.model.PaymentHistoryResponse
import com.application.scoo.data.repository.BoomDataRepository
import com.application.scoo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class PayRentalViewModel @Inject constructor(private val dataRepository: BoomDataRepository) :
    BaseViewModel() {

    private var mpaymentHistoryData: MutableLiveData<ViewState<PaymentHistoryResponse>> =
        MutableLiveData()

    fun getpaymentHistory(): MutableLiveData<ViewState<PaymentHistoryResponse>> {
        return mpaymentHistoryData
    }

    private fun onError(throwable: Throwable) {
        mpaymentHistoryData.value = NetworkError(throwable.message)
    }

    fun paymentHistory() {
        mpaymentHistoryData.value = Loading
        val header = "Bearer " + AppBase.sharedPreference?.getValueString(AppConstants.token).toString()
        val userId = AppBase.sharedPreference?.getValueString(AppConstants.userId).toString()
        val franchisevehicleId = AppBase.sharedPreference?.getValueString(AppConstants.franchisevehicleId).toString()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Create User Registration
            val otpData = dataRepository.getPaymentHistory(header, userId, franchisevehicleId)
            if (otpData.code == AppConstants.Success) {
                mpaymentHistoryData.value = Success(otpData)
            } else {
                mpaymentHistoryData.value = NetworkError(otpData.message)
            }
        }
    }

}
