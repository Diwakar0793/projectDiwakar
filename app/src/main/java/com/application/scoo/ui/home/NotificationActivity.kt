package com.application.scoo.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.scoo.R
import com.application.scoo.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity(), View.OnClickListener  {

    private var _notificationBinding: ActivityNotificationBinding? = null
    private val notificationBinding get() = _notificationBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _notificationBinding = ActivityNotificationBinding
            .inflate(layoutInflater)
        setContentView(notificationBinding.root)
        initData()
    }

    private fun initData() {
        notificationBinding.backBtn.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.backBtn -> {
                this.finish()
            }
        }
    }
}