package com.application.scoo.ui.home.map

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentLiveTrackingBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment


class LiveTrackingFragment : Fragment(),View.OnClickListener {

    private var _liveTrackingBinding : FragmentLiveTrackingBinding? = null
    private val liveTrackingBinding get() = _liveTrackingBinding!!

    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance() =
            LiveTrackingFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _liveTrackingBinding =
            FragmentLiveTrackingBinding.inflate(inflater, container, false)
        initData()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
        return liveTrackingBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private fun initData() {
        liveTrackingBinding.backBtn.setOnClickListener(this)
        liveTrackingBinding.IVPopup.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onClick(v: View?) {
        val fragment: Fragment
        when (v?.id) {
            R.id.backBtn -> {
                activity?.supportFragmentManager?.popBackStack()
            }

            R.id.IV_popup -> {
                showDialog()
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.popup_live_tracking)
        dialog.setCanceledOnTouchOutside(true)
        /*
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }*/
        dialog.show()

    }
}

