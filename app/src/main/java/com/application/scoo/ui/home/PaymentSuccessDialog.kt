package com.application.scoo.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.application.scoo.databinding.ActivityPaymentSuccessDialogBinding

class PaymentSuccessDialog : DialogFragment(), View.OnClickListener {
    private var _paysuccessBinding: ActivityPaymentSuccessDialogBinding? = null
    private val paysuccessBinding get() = _paysuccessBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _paysuccessBinding = ActivityPaymentSuccessDialogBinding.inflate(inflater, container, false)
        // make white background transparent
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val root: View = paysuccessBinding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
       // paysuccessBinding.btnPaySuccess.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
//            R.id.btn_pay_success -> {
//                dialog?.dismiss()
//            }
        }
    }
}