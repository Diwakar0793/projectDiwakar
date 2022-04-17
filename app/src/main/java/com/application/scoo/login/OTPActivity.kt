package com.application.scoo.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.application.scoo.ui.SelectEV
import dagger.android.AndroidInjection
import javax.inject.Inject


class OTPActivity : AppCompatActivity(), View.OnClickListener {

    private var _loginBinding: ActivityLoginBinding? = null
    private val loginBinding get() = _loginBinding!!
    private lateinit var loginViewModel: LoginViewModel

    private var _otpBinding: ActivityEnterOtpBinding? = null
    private val otpBinding get() = _otpBinding!!
    private lateinit var otpViewModel: OTPViewModel
    private lateinit var timer: CountDownTimer
    var timeCount = 90000L

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _otpBinding = ActivityEnterOtpBinding
            .inflate(layoutInflater)
        setContentView(otpBinding.root)
        initData()

        //OTP 1
        otpBinding.ETOtpDgt1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (otpBinding.ETOtpDgt1.text.toString().length == 1) {
                    otpBinding.ETOtpDgt1.clearFocus()
                    otpBinding.ETOtpDgt2.requestFocus()
                    otpBinding.ETOtpDgt2.setCursorVisible(true)
                }
                btnVerifyBGChange()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (otpBinding.ETOtpDgt1.text.toString().length == 0) {
                    otpBinding.ETOtpDgt1.requestFocus()
                }
                btnVerifyBGChange()
            }
        })

        //OTP2
        otpBinding.ETOtpDgt2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (otpBinding.ETOtpDgt2.text.toString().length == 1) {
                    otpBinding.ETOtpDgt2.clearFocus()
                    otpBinding.ETOtpDgt3.requestFocus()
                    otpBinding.ETOtpDgt3.setCursorVisible(true)
                }
                btnVerifyBGChange()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (otpBinding.ETOtpDgt2.text.toString().length == 0) {
                    otpBinding.ETOtpDgt1.requestFocus()
                }
                btnVerifyBGChange()
            }
        })

        //OTP 3
        otpBinding.ETOtpDgt3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (otpBinding.ETOtpDgt3.text.toString().length == 1) {
                    otpBinding.ETOtpDgt3.clearFocus()
                    otpBinding.ETOtpDgt4.requestFocus()
                    otpBinding.ETOtpDgt4.setCursorVisible(true)
                }
                btnVerifyBGChange()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (otpBinding.ETOtpDgt3.text.toString().length == 0) {
                    otpBinding.ETOtpDgt2.requestFocus()
                }
                btnVerifyBGChange()
            }
        })

        //OTP 4
        otpBinding.ETOtpDgt4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (otpBinding.ETOtpDgt4.text.toString().length == 1) {
                    otpBinding.ETOtpDgt4.requestFocus()
                    otpBinding.ETOtpDgt4.setCursorVisible(true)

                }
                btnVerifyBGChange()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (otpBinding.ETOtpDgt4.text.toString().length == 0) {
                    otpBinding.ETOtpDgt3.requestFocus()
                }
            }
        })

    }

    private fun initData() {
        AndroidInjection.inject(this)
         otpBinding.btnVerify.setOnClickListener(this)
        otpBinding.resendOtp.setOnClickListener(this)
        btnVerifyBGChange()
        loginViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        otpViewModel =
            ViewModelProvider(this, viewModelFactory).get(OTPViewModel::class.java)
        otpViewModel.getOtpVerify().observe(this, Observer { onOtpFetched(it) })
        OtpCheck()
    }

    private fun OtpCheck() {
        otpBinding.tvTimer.visibility = View.VISIBLE
        otpBinding.ETOtpDgt1.requestFocus()
        otpBinding.ETOtpDgt1.setCursorVisible(true)
        showTimer()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_verify -> {
//                if(otpBinding.btnVerify.text.trim().equals("Verify")) {
                    var otpString =
                        otpBinding.ETOtpDgt1.text.toString() + otpBinding.ETOtpDgt2.text.toString() +
                                otpBinding.ETOtpDgt3.text.toString() + otpBinding.ETOtpDgt4.text.toString()
                    val otpRequest = OTPRequest()
                    otpRequest.otp = otpString.toInt()
                    otpRequest.mobileNo =
                        AppBase.sharedPreference?.getValueString(AppConstants.contact).toString()
                    otpRequest.id =
                        AppBase.sharedPreference?.getValueString(AppConstants.id)?.toInt()
                    otpRequest.typeId = 4
                    otpViewModel.otpVerify(otpRequest)
              //  }
//                else{
//                    timer.cancel()
//                    showTimer()
//                    val loginRequest = loginOTPRequest()
//                    loginRequest.mobileNo =
//                        AppBase.sharedPreference?.getValueString(AppConstants.contact)
//                    loginViewModel.loginOTP(loginRequest)
//
//
//                }
            }
            R.id.resend_otp -> {
                timer.cancel()
//                showTimer()
                val loginRequest = loginOTPRequest()
                loginRequest.mobileNo =
                    AppBase.sharedPreference?.getValueString(AppConstants.contact)
                loginViewModel.loginOTP(loginRequest)
                showTimer()
            }
        }
    }


    private fun onOtpFetched(viewState: ViewState<OTPResponse>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
//                showError(viewState.message!!)
                Toast.makeText(this, "Please Enter the Correct OTP.!", Toast.LENGTH_LONG).show()
            }
            is Success -> {
                setProgress(false)
                Toast.makeText(this, "OTP Success", Toast.LENGTH_LONG).show()

                AppBase.sharedPreference?.save(
                    AppConstants.username,
                    viewState.mData.data?.users?.get(0)?.userName.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.emailid,
                    viewState.mData.data?.users?.get(0)?.emailId.toString()
                )
                val token = AppBase.sharedPreference?.save(
                    AppConstants.token,
                    viewState.mData.data?.token.toString()
                )
                Log.i("mAuth",token.toString())

                AppBase.sharedPreference?.setList(AppConstants.userArray, viewState.mData.data?.users)
                AppBase.sharedPreference?.save(
                    AppConstants.userId,
                    viewState.mData.data?.users?.get(0)?.userId.toString()
                )

                //plandetails
                AppBase.sharedPreference?.save(
                    AppConstants.planname,
                    viewState.mData.data?.users?.get(0)?.planName.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.plandesc,
                    viewState.mData.data?.users?.get(0)?.planDescription.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.planamt,
                    viewState.mData.data?.users?.get(0)?.planAmount.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.planduration,
                    viewState.mData.data?.users?.get(0)?.planFrequency.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.planend,
                    viewState.mData.data?.users?.get(0)?.planStartdate.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.planstart,
                    viewState.mData.data?.users?.get(0)?.planEnddate.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.franchisevehicleId,
                    viewState.mData.data?.users?.get(0)?.franchiseVehicleId.toString()
                )

                val userList = AppBase.sharedPreference?.getList(AppConstants.userArray)

                Log.i("Users :", userList.toString())

                callActivity()

                /*AppBase.sharedPreference?.save(
                    AppConstants.username,
                    viewState.mData.data?.thisUser?.userName.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.reg_num,
                    viewState.mData.data?.thisUser?.registrationNo.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.make,
                    viewState.mData.data?.thisUser?.make.toString()
                )
                AppBase.sharedPreference?.save(
                    AppConstants.model,
                    viewState.mData.data?.thisUser?.model.toString()
                )*/

            }
        }
    }

    private fun callActivity() {
        timer.cancel()
        startActivity(
            Intent(
                this,
                SelectEV::class.java
            )
        )
        finish()
    }

    private fun btnVerifyBGChange() {
        if (otpBinding.ETOtpDgt1.text?.isEmpty() == true || otpBinding.ETOtpDgt2.text?.isEmpty() == true ||
            otpBinding.ETOtpDgt3.text?.isEmpty() == true || otpBinding.ETOtpDgt4.text?.isEmpty() == true
        ) {
            otpBinding.btnVerify.setBackgroundResource(R.drawable.roundedrectanglebackgroundgrey)
            otpBinding.btnVerify.isClickable = false
        } else {
            otpBinding.btnVerify.setBackgroundResource(R.drawable.roundedrectanglegreenbackground)
            otpBinding.btnVerify.isClickable = true
        }
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        otpBinding.progressL.visibility = View.VISIBLE
    }

    fun hideProgress() {
        otpBinding.progressL.visibility = View.GONE
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showTimer() {
        timer = object : CountDownTimer(timeCount, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                otpBinding.tvTimer.setText("OTP expires in " + millisUntilFinished / 1000 + " seconds")
            }

            override fun onFinish() {
                otpBinding.ETOtpDgt1.text?.clear()
                otpBinding.ETOtpDgt2.text?.clear()
                otpBinding.ETOtpDgt3.text?.clear()
                otpBinding.ETOtpDgt4.text?.clear()
                otpBinding.ETOtpDgt1.requestFocus()
                otpBinding.ETOtpDgt1.setCursorVisible(true)
//                otpBinding.btnVerify.text = "Resend OTP"
//                otpBinding.btnVerify.setBackgroundResource(R.drawable.roundedrectanglegreenbackground)
            }
        }.start()
    }

}
