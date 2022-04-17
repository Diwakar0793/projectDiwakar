package com.application.scoo.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.scoo.R
import com.application.scoo.databinding.ActivityForgotPswrdBinding

class ForgotPswrd : AppCompatActivity(), View.OnClickListener {

    private var _frgtpswrdBinding : ActivityForgotPswrdBinding? = null
    private val frgtpswrdBinding get() = _frgtpswrdBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _frgtpswrdBinding = ActivityForgotPswrdBinding
            .inflate(layoutInflater)
        setContentView(frgtpswrdBinding.root)
        initData()

    }

    private fun initData() {
        frgtpswrdBinding.backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backBtn -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}