package com.application.scoo.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.scoo.AppBase
import com.application.scoo.common.*
import com.application.scoo.data.model.ChangePasswordRequest
import com.application.scoo.data.model.ChangePasswordResponse
import com.application.scoo.data.repository.BoomDataRepository
import com.application.scoo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(private val dataRepository: BoomDataRepository) :
    BaseViewModel() {

    private var mChangePasswordData: MutableLiveData<ViewState<ChangePasswordResponse>> =
        MutableLiveData()

    private fun onError(throwable: Throwable) {
        mChangePasswordData.value = NetworkError(throwable.message)
    }



    fun callChangePassword(): MutableLiveData<ViewState<ChangePasswordResponse>> {
        return mChangePasswordData
    }

    fun changePassword(request: ChangePasswordRequest) {
        mChangePasswordData.value = Loading
        val header = AppBase.sharedPreference?.getValueString(AppConstants.mAuth).toString()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(coroutineExceptionHandler) {

            val changePasswordData = dataRepository.changePassword(header, request)

            if (changePasswordData.success!!) {

                mChangePasswordData.value = Success(changePasswordData)
            } else {

                mChangePasswordData.value = NetworkError(changePasswordData.message)
            }
        }
    }


}
