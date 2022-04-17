package com.application.scoo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.application.scoo.common.AppConstants
import com.application.scoo.databinding.ActivityContactBinding
import com.application.scoo.databinding.ActivityCurrentPlanBinding

class CurrentPlan : AppCompatActivity(), View.OnClickListener {

    private var _currentPlanBinding: ActivityCurrentPlanBinding? = null
    private val currentPlanBinding get() = _currentPlanBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _currentPlanBinding = ActivityCurrentPlanBinding
            .inflate(layoutInflater)
        setContentView(currentPlanBinding.root)
        initData()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        currentPlanBinding.backBtn.setOnClickListener(this)
//        var PlanData = AppBase.sharedPreference?.getList(AppConstants.userArray)
//        var plan_name = AppBase.sharedPreference!!.getValueString(AppConstants.planname)
//        var plan_desc = AppBase.sharedPreference!!.getValueString(AppConstants.plandesc)
//        var plan_amt = AppBase.sharedPreference!!.getValueString(AppConstants.planamt)
//        var plan_dur = AppBase.sharedPreference!!.getValueString(AppConstants.planduration)
//
//
        currentPlanBinding.planName.text = AppBase.sharedPreference!!.getValueString(AppConstants.planname)
        currentPlanBinding.planDesc.text = AppBase.sharedPreference!!.getValueString(AppConstants.plandesc)
        currentPlanBinding.planAmt.text = "Rs."+ AppBase.sharedPreference!!.getValueString(AppConstants.planamt)
        currentPlanBinding.planDurn.text = AppBase.sharedPreference!!.getValueString(AppConstants.planduration)
        currentPlanBinding.planStart.text = AppBase.sharedPreference!!.getValueString(AppConstants.planstart)
        currentPlanBinding.planEnd.text = AppBase.sharedPreference!!.getValueString(AppConstants.planend)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backBtn -> {
                this.finish()
            }
        }
    }
}