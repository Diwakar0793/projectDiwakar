package com.application.scoo.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.scoo.common.*
import com.application.scoo.data.model.*
import com.application.scoo.data.repository.BoomDataRepository
import com.application.scoo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val dataRepository: BoomDataRepository) :
    BaseViewModel() {

   // private var mLoginLiveData: MutableLiveData<ViewState<LoginResponse>> = MutableLiveData()
    private var mLoginLiveData: MutableLiveData<ViewState<NewLoginResponse>> = MutableLiveData()


//    fun getLogin(): MutableLiveData<ViewState<LoginResponse>> {
//        return mLoginLiveData
//    }
    fun getLogin(): MutableLiveData<ViewState<NewLoginResponse>> {
        return mLoginLiveData
    }


    private fun onError(throwable: Throwable) {
        mLoginLiveData.value = NetworkError(throwable.message)
    }

//    fun userLogin(request: LoginRequest) {
//        mLoginLiveData.value = Loading
//        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
//            onError(exception)
//        }
//        // viewModelScope launch the new coroutine on Main Dispatcher internally
//        viewModelScope.launch(coroutineExceptionHandler) {
//            // Create User Registration
//            val userData = dataRepository.login(request)
//            // Return the result on main thread via Dispatchers.Main
//            if (userData.accessToken != null) {
//                mLoginLiveData.value = Success(userData)
//            } else {
//                mLoginLiveData.value = NetworkError("API is not return")
//            }
//        }
//    }

    fun userLogin(request: NewLoginRequest) {
        mLoginLiveData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Create User Registration
            var userData = dataRepository.login(request.apiKey!!, request.password!!,
                request.username!!
            )
           // userData = dataRepository.login(request)
            // Return the result on main thread via Dispatchers.Main
            if (userData.msg.equals("Success")) {
                mLoginLiveData.value = Success(userData)
            } else {
                mLoginLiveData.value = NetworkError(userData.msg)
            }
        }
    }


   // private var mUserLoginData: MutableLiveData<ViewState<LoginResponse>> = MutableLiveData()
    private var mLoginOTPData: MutableLiveData<ViewState<loginOTPResponse>> = MutableLiveData()


    fun getUserLoginResponseData(): MutableLiveData<ViewState<loginOTPResponse>> {
        return mLoginOTPData
    }

//
//    fun loginUser(request: LoginRequest) {
//        mUserLoginData.value = Loading
//        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
//            onError(exception)
//        }
//        // viewModelScope launch the new coroutine on Main Dispatcher internally
//        viewModelScope.launch(coroutineExceptionHandler) {
//            // Create User Registration
//            val userData = dataRepository.loginUser(request)
//
//            if (userData.code == AppConstants.Success) {
//                // Return the result on main thread via Dispatchers.Main
//                mUserLoginData.value = Success(userData)
//            } else {
//                mUserLoginData.value = NetworkError(userData.message)
//            }
//        }
//    }

    fun loginOTP(request: loginOTPRequest) {
        mLoginOTPData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Create User Registration
            val loginData = dataRepository.loginOTP(request)

            if (loginData.code == AppConstants.Success) {
                // Return the result on main thread via Dispatchers.Main
                mLoginOTPData.value = Success(loginData)
            } else {
                mLoginOTPData.value = NetworkError(loginData.message)
            }
        }
    }

}
