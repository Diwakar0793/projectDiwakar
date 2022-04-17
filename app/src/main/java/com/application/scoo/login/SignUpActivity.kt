package com.application.scoo.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.application.scoo.R
import com.application.scoo.databinding.ActivitySignupMsgBinding

import com.application.scoo.ui.base.BaseActivity


class SignUpActivity : BaseActivity(), View.OnClickListener {

    private var _signUpBinding : ActivitySignupMsgBinding? = null
    private val signUpBinding get() = _signUpBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _signUpBinding = ActivitySignupMsgBinding
            .inflate(layoutInflater)
        setContentView(signUpBinding.root)
        iniiData()
    }

    private fun iniiData() {
        signUpBinding.btnSignup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_signup -> {
                startActivity(
                    Intent(this,
                    LoginActivity::class.java)
                )
                this.finish()
            }
        }
    }


}
