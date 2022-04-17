package com.application.scoo.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
//    private val selected = MutableLiveData<SelectedFrag>()
//    private val mData = MutableLiveData<RegisterResponse>()
//    private val mUserBaseData = MutableLiveData<OtpResponse>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


//    fun setSelect(item: SelectedFrag) {
//        selected.value = item
//    }
//
//    fun getSelected(): LiveData<SelectedFrag> {
//        return selected
//    }
//
//    fun getRegisterData(): MutableLiveData<RegisterResponse> {
//        return mData
//    }
//
//    fun getUserBaseData(): MutableLiveData<OtpResponse> {
//        return mUserBaseData
//    }

}