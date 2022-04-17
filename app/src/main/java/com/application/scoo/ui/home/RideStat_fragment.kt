package com.application.scoo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.application.scoo.R
import com.application.scoo.databinding.FragmentRideStatFragmentBinding
import com.application.scoo.ui.base.BaseFragment


class RideStatfragment : BaseFragment(), View.OnClickListener {
   private var _rideStatBinding: FragmentRideStatFragmentBinding? = null
    private val rideStatBinding get() = _rideStatBinding!!
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }
    companion object {
        fun newInstance() =
            RideStatfragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _rideStatBinding =
            FragmentRideStatFragmentBinding.inflate(inflater, container, false)

        return rideStatBinding.root
      //  return inflater.inflate(R.layout.fragment_ride_stat_fragment, container, false)
    }

    private fun initData() {
        rideStatBinding.backBtn.setOnClickListener {
            // Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.popBackStack() }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        initViews()
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        return view
        initData()
        initViews()
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun initViews() {
       // rideStatBinding.backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val fragment: Fragment
        when (v?.id) {
//            R.id.backBtn -> {
//                activity?.supportFragmentManager?.popBackStack()
////                 loadFragment(fragment)
//            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}