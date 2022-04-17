package com.application.scoo.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.application.scoo.R
import com.application.scoo.databinding.ActivityLoginSignupBinding
import com.application.scoo.ui.base.BaseActivity


class LoginSignUpOptionActivity : BaseActivity(), View.OnClickListener {

    private var _loginSignUpBinding : ActivityLoginSignupBinding? = null
    private val loginSignUpBinding get() = _loginSignUpBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _loginSignUpBinding = ActivityLoginSignupBinding    .inflate(layoutInflater)
        setContentView(loginSignUpBinding.root)
        iniiData()

    }

    private fun iniiData() {
        loginSignUpBinding.btnLogin.setOnClickListener(this)
        loginSignUpBinding.btnSignup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_login -> {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    )
                )
            }
            R.id.btn_signup -> {
                startActivity(
                    Intent(this,
                        SignUpActivity::class.java)
                )
            }
        }
    }


}
