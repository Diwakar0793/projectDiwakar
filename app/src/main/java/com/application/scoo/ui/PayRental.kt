package com.application.scoo.ui

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.scoo.R
import com.application.scoo.common.*
import com.application.scoo.data.model.Datum
import com.application.scoo.data.model.PaymentHistoryResponse
import com.application.scoo.databinding.ActivityPayRentalBinding
import com.application.scoo.ui.home.adapter.PaymentHistoryAdapter
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import dagger.android.AndroidInjection
import org.json.JSONObject
import javax.inject.Inject


class PayRental : AppCompatActivity(), View.OnClickListener, PaymentResultListener {
    private var _payrentalBinding: ActivityPayRentalBinding? = null
    private val payrentalBinding get() = _payrentalBinding!!

    private lateinit var mPayRentalViewModel: PayRentalViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var paymentType: String? = ""
    private var amount: String? = ""
    private var orderNo: String? = ""
    private var PAYMENT_TYPE: String? = "PaymentType"
    private var AMOUNT: String? = "Amount"
    private var ORDER_NO: String? = "OrderNo"

    private var paymentHistoryAdapter: PaymentHistoryAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _payrentalBinding = ActivityPayRentalBinding
            .inflate(layoutInflater)
        setContentView(payrentalBinding.root)
        iniiData()
        initViews()

    }

    private fun initViews() {
        mLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        payrentalBinding.RVPaymentHistory.layoutManager = mLayoutManager
    }

    private fun iniiData() {
        AndroidInjection.inject(this)
        payrentalBinding.backBtn.setOnClickListener(this)
        payrentalBinding.btnPay.setOnClickListener(this)

        mPayRentalViewModel =
            ViewModelProvider(this, viewModelFactory).get(PayRentalViewModel::class.java)
        mPayRentalViewModel.paymentHistory()
        mPayRentalViewModel.getpaymentHistory().observe(this, Observer { onPaymentHistoryFetched(it) })
    }



    private fun onPaymentHistoryFetched(viewState: ViewState<PaymentHistoryResponse>) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                populatePaymentHistory(viewState.mData.data)
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun populatePaymentHistory(mData: List<Datum>?) {
        paymentHistoryAdapter = PaymentHistoryAdapter(mData)
        payrentalBinding.RVPaymentHistory.adapter = paymentHistoryAdapter
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backBtn -> {
                this.finish()
            }
            R.id.btn_pay -> {
                startPayment()
//                showDialog()
            }
        }
    }


    fun startPayment() {

        val activity: Activity = this
        val co = Checkout()
        try {
            val options = JSONObject()
            options.put("name", "Scoo")
            options.put("description", "Monthly Charges")
            options.put("send_sms_hash", true)
            options.put("allow_rotation", true)
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency", "INR")
            options.put("amount", "100")
            val preFill = JSONObject()
            preFill.put("email", "test@razorpay.com")
            preFill.put("contact", "9876543210")
            options.put("prefill", preFill)
            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_SHORT)
                .show()
            e.printStackTrace()
        }
    }




    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_payment_success_dialog)
        dialog.setCanceledOnTouchOutside(true)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.show()
    }

    override fun onPaymentSuccess(razorpayPaymentID: String?) {
        try {
            Toast.makeText(this, "Payment Successful: $razorpayPaymentID", Toast.LENGTH_SHORT)
                .show()
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentError(code: Int, response: String?) {
        try {
            Toast.makeText(
                this,
                "Payment failed: " + code.toString() + " " + response,
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "Exception in onPaymentError", e)
        }
    }


    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        payrentalBinding.progressP.visibility = View.VISIBLE
    }

    fun hideProgress() {
        payrentalBinding.progressP.visibility = View.GONE
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

}