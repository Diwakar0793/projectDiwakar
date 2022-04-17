package com.application.scoo.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.application.scoo.AppBase
import com.application.scoo.R
import com.application.scoo.common.*
import com.application.scoo.databinding.ActivityChangePasswordBinding

import com.application.scoo.ui.base.BaseActivity
import androidx.lifecycle.Observer
import com.application.scoo.data.model.ChangePasswordRequest
import com.application.scoo.data.model.ChangePasswordResponse
import dagger.android.AndroidInjection
import javax.inject.Inject


class ChangePasswordActivity : BaseActivity(), View.OnClickListener {

    private var _changePasswordBinding : ActivityChangePasswordBinding? = null
    private val changePasswordBinding get() = _changePasswordBinding!!

    private lateinit var mchangePasswordViewModel: ChangePasswordViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _changePasswordBinding = ActivityChangePasswordBinding
            .inflate(layoutInflater)
        setContentView(changePasswordBinding.root)
        iniiData()
    }

    private fun iniiData() {
        AndroidInjection.inject(this)
        changePasswordBinding.btnSavePw.setOnClickListener(this)

        mchangePasswordViewModel =
            ViewModelProvider(this, viewModelFactory).get(ChangePasswordViewModel::class.java)
        mchangePasswordViewModel.callChangePassword().observe(this,  Observer { onChangePasswordFetched(it) })

    }

    private fun onChangePasswordFetched(viewState: ViewState<ChangePasswordResponse>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                callActivity(viewState.mData)
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun callActivity(data: ChangePasswordResponse) {
        if (data.success == true) {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
            finish()
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
           R.id.btn_save_pw -> {
               if (changePasswordBinding.newPw.text?.isEmpty() == false
                   || changePasswordBinding.confrmPswrd.text?.isEmpty() == false
                   || changePasswordBinding.currentPw.text?.isEmpty() ==false) {

                       if (changePasswordBinding.newPw.text?.trim() == changePasswordBinding.confrmPswrd.text?.trim()) {
//                           AppConstants.otpVerified = true
                           val userId = AppBase.sharedPreference?.getValueString(AppConstants.userId).toString()

                           val changePasswordReq = ChangePasswordRequest()
                           changePasswordReq.userId = userId
                           changePasswordReq.currentPassword = changePasswordBinding.currentPw.text?.toString()
                           changePasswordReq.newPassword = changePasswordBinding.newPw.text?.toString()


                           mchangePasswordViewModel.changePassword(changePasswordReq)
                       } else {
                           Toast.makeText(this, "Password mismatch. !", Toast.LENGTH_LONG).show()
                       }
               } else {
                   Toast.makeText(this, "Please enter all fields. !", Toast.LENGTH_LONG).show()
               }
           }
        }
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        changePasswordBinding.progressL.visibility = View.VISIBLE
    }

    fun hideProgress() {
        changePasswordBinding.progressL.visibility = View.GONE
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }


}
