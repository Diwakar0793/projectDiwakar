package com.application.scoo.ui.home.Dashboard

import com.application.scoo.data.repository.BoomDataRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val dataRepository: BoomDataRepository) {


//    fun userDashboard(request: NewLoginRequest) {
//        mDashboardData.value = Loading
//        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
//            onError(exception)
//        }
//        // viewModelScope launch the new coroutine on Main Dispatcher internally
//        viewModelScope.launch(coroutineExceptionHandler) {
//            // Create User Registration
//            var userData = dataRepository.login(request.apiKey!!, request.password!!,
//                request.username!!
//            )
//            // userData = dataRepository.login(request)
//            // Return the result on main thread via Dispatchers.Main
//            if (userData.msg.equals("Success")) {
//                mDashboardData.value = Success(userData)
//            } else {
//                mDashboardData.value = NetworkError(userData.msg)
//            }
//        }
//    }
}