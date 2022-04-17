package com.application.scoo.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentRoadsideAssistenceBinding
import com.application.scoo.ui.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class RoadAssistenceFragment : BaseFragment(), View.OnClickListener {
    private var _roadsideassistencebinding: FragmentRoadsideAssistenceBinding? = null
    private val roadsideAssistenceBinding get() = _roadsideassistencebinding!!
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance() =
            RoadAssistenceFragment()
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

        _roadsideassistencebinding =
            FragmentRoadsideAssistenceBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
        return roadsideAssistenceBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        roadsideAssistenceBinding.IVSendAssistance.setOnClickListener(this)
        roadsideAssistenceBinding.backBtn.setOnClickListener {
            //Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.popBackStack() }    }

    override fun onClick(v: View?) {
        val fragment: Fragment
        when (v?.id) {
            R.id.IV_send_assistance -> {
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
        dialog.setContentView(R.layout.popup_sending_assistance)
        dialog.setCanceledOnTouchOutside(true)
        /*
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }*/
        dialog.show()

    }



}