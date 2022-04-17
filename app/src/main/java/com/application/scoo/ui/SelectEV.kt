package com.application.scoo.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import com.application.scoo.AppBase
import com.application.scoo.R
import com.application.scoo.common.AppConstants
import com.application.scoo.data.model.UserDetails
import com.application.scoo.databinding.ActivitySelectEvBinding
import com.application.scoo.ui.home.MenuActivity
import kotlin.toString


class SelectEV : AppCompatActivity(), View.OnClickListener {

    private var _selectevBinding : ActivitySelectEvBinding? = null
    private val selectevBinding get() = _selectevBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _selectevBinding = ActivitySelectEvBinding
            .inflate(layoutInflater)
        setContentView(selectevBinding.root)
        initData()

    }

    private fun initData() {
        selectevBinding.backBtn.setOnClickListener(this)
        var userList = AppBase.sharedPreference?.getList(AppConstants.userArray)

        Log.i("Users :", userList.toString())


        if (userList != null) {
            populateRadioButtons(userList)
            selectevBinding.TVMappedVeh.visibility = View.GONE
        } else {
            selectevBinding.TVMappedVeh.visibility = View.VISIBLE
        }



        selectevBinding.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{radioGroup: RadioGroup?, i: Int ->
            AppBase.sharedPreference?.save(
                AppConstants.make,
                userList?.get(i)!!.make.toString()
            )

            AppBase.sharedPreference?.save(
                AppConstants.model,
                userList?.get(i)!!.model.toString()
            )

            AppBase.sharedPreference?.save(
                AppConstants.reg_num,
                userList?.get(i)!!.registrationNo.toString()
            )

            AppBase.sharedPreference?.save(
                AppConstants.vehicleId,
                userList?.get(i)!!.vehicleId.toString()
            )
            callActivity()
        })


        /*selectevBinding.RBAmpereFx.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("vehicle", "Ampere  Magnus-FX  KA05AB3452")
            startActivity(intent)
        })

        selectevBinding.RBAmperePro.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("vehicle", "Ampere Magnus-Pro KA02HJ7845")

            startActivity(intent)
        })*/
//
//        selectevBinding.RBAther.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this, MenuActivity::class.java)
//            intent.putExtra("vehicle", "Ather Ather-450X KA03hj6896")
//
//            startActivity(intent)
//        })
    }


    @SuppressLint("SetTextI18n")
    private fun populateRadioButtons(userList: List<UserDetails?>?) {
        for (i in 0 until userList!!.size) {
            val radioButton = RadioButton(this)
            radioButton.setText(userList[i]!!.make.toString() + " " + userList[i]!!.model.toString() + " "
                    + userList[i]!!.registrationNo)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                radioButton.setTextAppearance(this, android.R.style.TextAppearance_Material_Small)
            }
            radioButton.marginBottom
            radioButton.setId(i)
            var params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10,10,10,10)
            selectevBinding.radioGroup.addView(radioButton, params)
        }
    }

    private fun callActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backBtn -> {
                this.finish()
            }
        }
    }
}