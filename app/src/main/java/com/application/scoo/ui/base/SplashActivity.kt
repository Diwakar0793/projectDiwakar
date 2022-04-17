package com.application.scoo.ui.base

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.application.scoo.R
import com.application.scoo.login.LoginActivity


class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showAnim()
    }

    private fun showAnim() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                openLoginAnimation()
            }

            override fun onTick(p0: Long) {}
        }.start()
    }

    private fun openLoginAnimation(){

            startActivity(Intent(this, LoginActivity::class.java))
            //startActivity(Intent(this, TestActivity::class.java))
            finish()


    }

}