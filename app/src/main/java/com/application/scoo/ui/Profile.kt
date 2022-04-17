package com.application.scoo.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.scoo.AppBase
import com.application.scoo.R
import com.application.scoo.common.AppConstants
import com.application.scoo.databinding.ActivityProfileBinding

class Profile : AppCompatActivity(), View.OnClickListener {

    private var _profileBinding : ActivityProfileBinding? = null
    private val profileBinding get() = _profileBinding!!
    var username = AppBase.sharedPreference!!.getValueString(AppConstants.username)
    var mobileno = AppBase.sharedPreference!!.getValueString(AppConstants.contact)
    var emailid = AppBase.sharedPreference!!.getValueString(AppConstants.emailid)
    var reg_num = AppBase.sharedPreference!!.getValueString(AppConstants.reg_num)
    var make = AppBase.sharedPreference!!.getValueString(AppConstants.make)
    var model = AppBase.sharedPreference!!.getValueString(AppConstants.model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _profileBinding = ActivityProfileBinding
            .inflate(layoutInflater)
        setContentView(profileBinding.root)
        initData()

    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        profileBinding.backBtn.setOnClickListener(this)
        profileBinding.etFirstname.setText(username)
        profileBinding.etEmail.setText(emailid)
        profileBinding.etPhone.setText(mobileno)
        profileBinding.regNum.setText(reg_num)
        profileBinding.make.setText(make)
        profileBinding.model.setText(model)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backBtn -> {
                this.finish()
            }
        }
    }
}