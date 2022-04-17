package com.application.scoo.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.scoo.R
import com.application.scoo.common.Loading
import com.application.scoo.common.NetworkError
import com.application.scoo.common.Success
import com.application.scoo.common.ViewState
import com.application.scoo.data.model.DashboardResponse
import com.application.scoo.databinding.ActivityDashboardBinding
import com.application.scoo.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection


class BatteryInfoFragment1 : BaseFragment(), View.OnClickListener {

    private var _dashboardBinding : ActivityDashboardBinding? = null
    private val dashboardBinding get() = _dashboardBinding!!

    private lateinit var mDashboardViewModel: DashboardViewModel


    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _dashboardBinding = ActivityDashboardBinding    .inflate(layoutInflater)
        setContentView(R.layout.activity_dashboard)
        initData()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _dashboardBinding =
            ActivityDashboardBinding.inflate(inflater, container, false)
        initData()
        return dashboardBinding.root

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun initData() {
//        dashboardBinding.LLBatteryInfo.setOnClickListener(this)
//        dashboardBinding.LLRangeLeft.setOnClickListener(this)

        mDashboardViewModel =
            ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
        mDashboardViewModel.callDashboardData().observe(viewLifecycleOwner,  Observer { onDashboardDataFetched(it) })
        mDashboardViewModel.getDashboardData()
    }

    private fun onDashboardDataFetched(viewState: ViewState<DashboardResponse>) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
               // Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                populateDashboardData(viewState.mData)

            }
        }
    }

    private fun populateDashboardData(mData: DashboardResponse) {
        var batteryLevel = mData.data?.batteryLeft
        var rangeLeft = mData.data?.rangeLeft
        var variantName = mData.data?.variantName

//        dashboardBinding.TVBatteryLeft.text = batteryLevel
//        dashboardBinding.TVBatteryInfo.text = batteryLevel
//        dashboardBinding.TVRangeLeft.text = rangeLeft
      //  dashboardBinding.TVVariantName.text = variantName
    }

    fun showError(message: String) {
        //Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
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

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.ride_statistics -> {
            }

//            R.id.LL_battery_info -> {
//                val fragment: Fragment
//                fragment = BatteryInfoFragment()
//                loadFragment(fragment)
//            }
//
//            R.id.LL_range_left -> {
//                val fragment: Fragment
//                fragment = LiveTrackingFragment()
//                loadFragment(fragment)
//            }

            }
        }

    fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        //transaction.addToBackStack(null)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }



}
