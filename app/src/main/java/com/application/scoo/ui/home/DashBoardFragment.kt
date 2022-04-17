package com.application.scoo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.application.scoo.AppBase
import com.application.scoo.CurrentPlan
import com.application.scoo.R
import com.application.scoo.common.*
import com.application.scoo.data.model.*
import com.application.scoo.databinding.ActivityDashboardBinding
import com.application.scoo.ui.base.BaseFragment
import com.application.scoo.ui.home.map.MapsActivity


class DashBoardFragment : BaseFragment(), View.OnClickListener {

    private var _dashboardBinding: ActivityDashboardBinding? = null
    private val dashboardBinding get() = _dashboardBinding!!
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private lateinit var mDashboardViewModel: DashboardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // return inflater.inflate(R.layout.activity_dashboard, container, false)
        _dashboardBinding =
            ActivityDashboardBinding.inflate(inflater, container, false)

        return dashboardBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initViews()
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initData()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun initData() {
        dashboardBinding.OnOff.setImageResource(R.drawable.ic_power_grey)
        dashboardBinding.OnOff.setOnClickListener(this)
        dashboardBinding.curPlan.setOnClickListener(this)
        dashboardBinding.check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(requireContext(), "checked", Toast.LENGTH_LONG).show()
                dashboardBinding.OnOff.setImageResource(R.drawable.ic_powerbtn_red)
                dashboardBinding.OnOff.isEnabled = true
            } else {
                Toast.makeText(requireContext(), "unchecked", Toast.LENGTH_LONG).show()
                dashboardBinding.OnOff.isEnabled = false
                dashboardBinding.OnOff.setImageResource(R.drawable.ic_power_grey)

            }
        }
        dashboardBinding.locateBike.setOnClickListener(this)
        mDashboardViewModel =
            ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
        mDashboardViewModel.callDashboardData()
            .observe(viewLifecycleOwner, Observer { onDashboardDataFetched(it) })
        // mDashboardViewModel.getDashboardData()
//        mDashboardViewModel.callBattData()
//            .observe(viewLifecycleOwner, Observer { onBatteryInfoReceived(it) })
//        val batteryInfoRequest = BatteryInfoRequest()
//        batteryInfoRequest.apiKey = "f972956e4b0905f332342f6c0579d7e7"
//        batteryInfoRequest.loginID = "33"
//        batteryInfoRequest.imei = "352913090328285"
//        batteryInfoRequest.vehicleNumber = "BLR_DEMO1"

     //   mDashboardViewModel.BatteryInfoData(batteryInfoRequest)
        dashboardBinding.OnOff.setOnClickListener(this)
        mDashboardViewModel.callVehicleOnOff()
            .observe(viewLifecycleOwner, Observer { vehOnFetched(it) })
        mDashboardViewModel.callTripOnOff()
            .observe(viewLifecycleOwner, Observer { tripOnFetched(it) })
    }

    private fun tripOnFetched(viewState: ViewState<TripOnOffResponse>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                AppBase.sharedPreference!!.getValueString(AppConstants.loginID)
                Toast.makeText(requireContext(), viewState.mData.data, Toast.LENGTH_LONG).show()
                AppBase.sharedPreference?.save(
                    AppConstants.tripstatus,
                    viewState.mData.data.toString()
                )
                var tripstatus = AppBase.sharedPreference!!.getValueString(AppConstants.tripstatus)
//                if ( tripstatus.equals("Trip Start")) {
//                    dashboardBinding.OnOff.setImageResource(R.drawable.power_on_1)
//                } else if( tripstatus.equals("Trip Stop")){
//                    dashboardBinding.OnOff.setImageResource(R.drawable.ic_powerbtn_red)
//                }else{
//
//                }

            }

        }
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
                //    Toast.makeText(activity, "Battery Info Success", Toast.LENGTH_LONG).show()
                populateBatteryInfoData(viewState.mData)

            }
        }
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
                Toast.makeText(activity, viewState.mData.data.toString(), Toast.LENGTH_LONG).show()
                populateDashboardData(viewState.mData)

            }
        }
    }

    private fun vehOnFetched(viewState: ViewState<VehicleOnResponse>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                AppBase.sharedPreference!!.getValueString(AppConstants.loginID)
                Toast.makeText(requireContext(), viewState.mData.data, Toast.LENGTH_LONG).show()
                AppBase.sharedPreference?.save(
                    AppConstants.vehiclestatus,
                    viewState.mData.data.toString()
                )
                var vehicleStatus =
                    AppBase.sharedPreference!!.getValueString(AppConstants.vehiclestatus)
//                if ( vehicleStatus.equals("Vehicle On")) {
//                    dashboardBinding.OnOff.setImageResource(R.drawable.power_on_1)
//                } else if ( vehicleStatus.equals("Vehicle Off")){
//                    dashboardBinding.OnOff.setImageResource(R.drawable.ic_powerbtn_red)
//                }else{
//
//                }

            }

        }
    }

    private fun populateDashboardData(mData: DashboardResponse) {
        // var batteryLevel = mData.message
//        var rangeLeft = mData.data?.rangeLeft
//        var variantName = mData.data?.variantName
//
//        dashboardBinding.TVBatteryLeft.text = batteryLevel
//        dashboardBinding.TVBatteryInfo.text = batteryLevel
//        dashboardBinding.TVRangeLeft.text = rangeLeft
//        dashboardBinding.TVVariantName.text = variantName
    }


    private fun populateBatteryInfoData(mData: BatteryInfoResponse) {
        val batteryLevel = mData.data!![0].soc
        AppBase.sharedPreference?.save(
            AppConstants.batteryLevel,
            mData.data!![0].soc.toString()
        )
//        dashboardBinding.TVBatteryLeft.text = batteryLevel
//        dashboardBinding.TVBatteryInfo.text = batteryLevel
    }

    fun showError(message: String) {
        //  Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
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
        val fragment: Fragment
        when (v?.id) {
            R.id.OnOff -> {

                var vehicleStatus =
                    AppBase.sharedPreference!!.getValueString(AppConstants.vehiclestatus)
                var tripstatus =
                    AppBase.sharedPreference!!.getValueString(AppConstants.tripstatus)

                val vehicleOnRequest = VehicleOnRequest()
                val tripOnOffRequest = TripOnOffRequest()
                vehicleOnRequest.api_key = "f972956e4b0905f332342f6c0579d7e7"
                vehicleOnRequest.LoginID = "33"
                vehicleOnRequest.imei = "352913090328285"
                tripOnOffRequest.api_key = "f972956e4b0905f332342f6c0579d7e7"
                tripOnOffRequest.LoginID = "33"
                tripOnOffRequest.imei = "352913090328285"

                if ((vehicleStatus.equals("Vehicle On") || vehicleStatus.equals("vehiclestatus")) && (tripstatus.equals(
                        "Trip Start"
                    ) || tripstatus.equals("tripstatus"))
                ) {
                    vehicleOnRequest.veh_status = "0"
                    tripOnOffRequest.trip_status = "0"
                } else {
                    tripOnOffRequest.trip_status = "1"
                    vehicleOnRequest.veh_status = "1"

                }
                mDashboardViewModel.VehicleOnOff(vehicleOnRequest)
                mDashboardViewModel.TripOnOff(tripOnOffRequest)
            }

            R.id.ride_statistics -> {
                fragment = RideStatfragment.newInstance()
                loadFragment(fragment)

            }
            R.id.locate_bike -> {
                val intent = Intent(requireContext(), MapsActivity::class.java)
                startActivity(intent)
            }
            R.id.cur_plan -> {
                val intent = Intent(requireContext(), CurrentPlan::class.java)
                startActivity(intent)
            }
        }
    }

    fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        //transaction?.disallowAddToBackStack()
        transaction?.commit()

    }

}
