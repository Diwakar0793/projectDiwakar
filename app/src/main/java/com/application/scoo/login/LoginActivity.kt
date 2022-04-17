package com.application.scoo.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.scoo.AppBase
import com.application.scoo.R
import com.application.scoo.common.*
import com.application.scoo.data.model.*
import com.application.scoo.databinding.ActivityEnterOtpBinding
import com.application.scoo.databinding.ActivityLoginBinding

import dagger.android.AndroidInjection
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var _loginBinding: ActivityLoginBinding? = null
    private val loginBinding get() = _loginBinding!!
    private lateinit var loginViewModel: LoginViewModel
    private var _otpBinding: ActivityEnterOtpBinding? = null
    private val otpBinding get() = _otpBinding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _loginBinding = ActivityLoginBinding
            .inflate(layoutInflater)
        setContentView(loginBinding.root)
        iniiData()
    }

    private fun iniiData() {
        AndroidInjection.inject(this)
        loginBinding.btnLoginUser.setOnClickListener(this)
        loginBinding.frgtPswrd.setOnClickListener(this)
//        loginBinding.ETEmail.text = Editable.Factory.getInstance().newEditable("9448387974")
        loginBinding.ETEmail.text = Editable.Factory.getInstance().newEditable("6362052769")
//        loginBinding.ETPw.text = Editable.Factory.getInstance().newEditable("123456")
        loginBinding.btnLoginUser.setOnClickListener(this)
        loginViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        loginViewModel.getUserLoginResponseData().observe(this, Observer { onLoginFetched(it) })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login_user -> {
                if (loginBinding.ETEmail.text?.isNotEmpty() == true) {
                    val loginRequest = loginOTPRequest()
                    loginRequest.mobileNo = loginBinding.ETEmail.text?.toString()
                    loginViewModel.loginOTP(loginRequest)
                } else {
                    Toast.makeText(
                        this,
                        "Invalid Mobile Number!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            R.id.frgt_pswrd -> {
                val intent = Intent(this, ForgotPswrd::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onLoginFetched(viewState: ViewState<loginOTPResponse>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                callActivity(viewState.mData)
//               var email =  AppBase.sharedPreference?.save(AppConstants.emailid, viewState.mData.data?.thisUser?.emailId .toString())
//                Log.i("email", email.toString())
                viewState.mData.data?.id?.let { AppBase.sharedPreference?.save(AppConstants.id, it) }
                viewState.mData.data?.contact?.let {
                    AppBase.sharedPreference?.save(AppConstants.contact,
                        it
                    )
                }
//                showTimer()
//                otpBinding.btnVerify.text = "Resend OTP"
//                otpBinding.btnVerify.setBackgroundResource(R.drawable.roundedrectanglegreenbackground)
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callActivity(data: loginOTPResponse) {
        startActivity(
            Intent(
                this,
               OTPActivity::class.java
           // SelectEV::class.java
            )
        )
        finish()
    }


    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        loginBinding.progressL.visibility = View.VISIBLE
    }

    fun hideProgress() {
        loginBinding.progressL.visibility = View.GONE
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }


}
