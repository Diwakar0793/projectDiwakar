package com.application.scoo.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.scoo.R
import com.application.scoo.databinding.ActivityContactBinding

class Contact : AppCompatActivity(), View.OnClickListener {

    private var _contactBinding: ActivityContactBinding? = null
    private val contactBinding get() = _contactBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _contactBinding = ActivityContactBinding
            .inflate(layoutInflater)
        setContentView(contactBinding.root)
        iniiData()
    }

    private fun iniiData() {
        contactBinding.backBtn.setOnClickListener(this)
        contactBinding.emailClick.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO,
            Uri.fromParts("mailto","ops@scooev.com",null))
            startActivity(Intent.createChooser(intent, "send email..."))
        }
        val phonenumber= "+91 97402 46484"
        contactBinding.mobileClick.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
               intent.data = Uri.parse("tel:"+ phonenumber)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backBtn -> {
                this.finish()
            }
        }
    }
}