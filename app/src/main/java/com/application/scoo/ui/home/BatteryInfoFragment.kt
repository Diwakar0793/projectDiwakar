package com.application.scoo.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.scoo.AppBase
import com.application.scoo.R
import com.application.scoo.common.*
import com.application.scoo.data.model.BatteryInfoResponse
import com.application.scoo.databinding.ActivityDashboardBinding
import com.application.scoo.databinding.FragmentBatteryInfoBinding
import com.application.scoo.ui.base.BaseFragment
import com.application.scoo.ui.home.adapter.BatteryInfoAdapter
import dagger.android.support.AndroidSupportInjection


class BatteryInfoFragment : BaseFragment(), View.OnClickListener {

    private var _batteryInfoBinding : FragmentBatteryInfoBinding? = null
    private val batteryInfoBinding get() = _batteryInfoBinding!!
    private var _dashboardBinding: ActivityDashboardBinding? = null
    private val dashboardBinding get() = _dashboardBinding!!

    var batteryLevel: String? = null

    companion object {
        fun newInstance() =
            BatteryInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _batteryInfoBinding =
            FragmentBatteryInfoBinding.inflate(inflater, container, false)
        batteryLevel = AppBase.sharedPreference!!.getValueString(AppConstants.batteryLevel)

        val batteries = mutableListOf(
            batteryLevel
        )

        val layoutManager = LinearLayoutManager(
            activity, // context
            RecyclerView.HORIZONTAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            batteryInfoBinding.recyclerView.layoutManager = this
        }
        BatteryInfoAdapter(batteries).apply {
            batteryInfoBinding.recyclerView.adapter = this
        }
        initData()
        return batteryInfoBinding.root

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun initData() {
        batteryInfoBinding.backBtn.setOnClickListener(this)
        var batteryUsed = 100 - Integer.parseInt(batteryLevel)
        batteryInfoBinding.battUse.text = batteryUsed.toString() + "%"
    }

    override fun onClick(p0: View?) {
        val fragment: Fragment
        when (p0?.id) {
            R.id.backBtn -> {
                activity?.supportFragmentManager?.popBackStack()
                // loadFragment(fragment)
            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun onBatteryInfoReceived(viewState: ViewState<BatteryInfoResponse>) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
             //   Toast.makeText(activity, "Battery Info Success", Toast.LENGTH_LONG).show()
                populateBatteryInfoData(viewState.mData)

            }
        }
    }
    private fun populateBatteryInfoData(mData: BatteryInfoResponse) {
        val percent = 100
        val batteryLevel = mData.data!![0].soc
        val batteryhealth = mData.data!![0].soh
        var used = Integer.parseInt(mData.data!![0].soc)
        var batteryusage = 100 - used
        //val batteryusage = percent.minus(100,mData.data!![0].soc.toString())
        //var used = 100 - mData.data!![0].soc
        val batterytemp = mData.data!![0].batteryTemperature
         batteryInfoBinding.battUse.text = batteryusage.toString()
        batteryInfoBinding.TVBattHealth.text = batteryhealth
        batteryInfoBinding.TVTmp.text = batterytemp
       // batteryInfoBinding.battUse.text = batteryusage

    }

    fun showError(message: String) {
       // Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        dashboardBinding.progressL.visibility = View.VISIBLE
    }

    fun hideProgress() {
        dashboardBinding.progressL.visibility = View.GONE
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }
}
