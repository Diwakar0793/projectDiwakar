
package com.application.scoo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.application.scoo.R
import com.application.scoo.databinding.FragmentServiceInfoBinding
import com.application.scoo.ui.base.BaseFragment

class ServiceInfo : BaseFragment(), View.OnClickListener {
    private var _serviceInfoBinding: FragmentServiceInfoBinding? = null
    private val serviceInfoBinding get() = _serviceInfoBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _serviceInfoBinding =
            FragmentServiceInfoBinding.inflate(inflater, container, false)

        return serviceInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        serviceInfoBinding.LLRoadsideAssistance.setOnClickListener(this)
        serviceInfoBinding.servBook.setOnClickListener(this)
        serviceInfoBinding.servHistory.setOnClickListener(this)
        serviceInfoBinding.backbtn.setOnClickListener {
           // Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.popBackStack() }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    companion object {
        fun newInstance() = ServiceInfo()
    }

    override fun onClick(v: View?) {
        val fragment: Fragment
        when (v?.id) {
                R.id.LL_roadside_assistance -> {
                    fragment = RoadAssistenceFragment.newInstance()
                    loadFragment(fragment)

                }
            R.id.serv_book -> {
                fragment = ServiceBookFragment.newInstance()
                loadFragment(fragment)
            }
            R.id.serv_history -> {
                fragment = ServiceHistoryFragment.newInstance()
                loadFragment(fragment)
            }

            R.id.LL_roadside_assistance -> {
                fragment = RoadSideAssistanceFragment.newInstance()
                loadFragment(fragment)
            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}