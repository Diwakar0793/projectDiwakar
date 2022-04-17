package com.application.scoo.ui.home.bottomNavViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentServiceBinding
import com.application.scoo.ui.home.bottomNavViews.service.PastServiceFragment
import com.application.scoo.ui.home.bottomNavViews.service.RequestServiceFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class ServiceFragment : Fragment() {

    private var _serviceFragmentBinding: FragmentServiceBinding? = null

    private val serviceFragmentBinding get() = _serviceFragmentBinding!!

    private lateinit var serviceNavigation: TabLayout

    private val fragment1: Fragment = PastServiceFragment()

    private var active = fragment1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _serviceFragmentBinding =
            FragmentServiceBinding.inflate(inflater, container, false)


        return serviceFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callDefaultTab()
        initViews()
    }

    private fun callDefaultTab() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.tab_frame_container, active, active.javaClass.simpleName)
            ?.disallowAddToBackStack()
            //    .addToBackStack(null)
            ?.commit()
    }

    private fun initViews() {
        serviceNavigation = serviceFragmentBinding.tabLayout
        serviceNavigation.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment : Fragment
                when (serviceNavigation.selectedTabPosition) {
                    0 -> {
                        fragment = PastServiceFragment()
                        loadFragment(fragment)

                    }
                    1 -> {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Tab " + tabLayout.getSelectedTabPosition(),
//                            Toast.LENGTH_LONG
//                        ).show()
                    }
                    2 -> {
                        fragment = RequestServiceFragment()
                        loadFragment(fragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.tab_frame_container, fragment)
        //transaction.addToBackStack(null)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }
}

   
     






