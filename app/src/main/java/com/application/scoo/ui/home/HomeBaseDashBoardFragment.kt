package com.application.scoo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.scoo.ui.home.bottomNavViews.HomeFragment

import com.application.scoo.R
import com.application.scoo.databinding.FragmentHomeBaseDashboardBinding
import com.application.scoo.ui.home.bottomNavViews.ServiceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeBaseDashBoardFragment : Fragment() {

    private var _homeBaseDashboardBinding: FragmentHomeBaseDashboardBinding? = null

    private val homeBaseDashboardBinding get() = _homeBaseDashboardBinding!!

    //  val navController = findNavController(R.id.frame_container)

//    private val fragment1: Fragment = TripFragment()
//    private var active = fragment1

    lateinit var navigation: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _homeBaseDashboardBinding =
            FragmentHomeBaseDashboardBinding.inflate(inflater, container, false)



        return homeBaseDashboardBinding.root
    }

    /*private fun initViews() {
        navigation = homeBaseDashboardBinding.bottomNavigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.clearAnimation()

    }*/

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            val fragment: Fragment
            when (it.itemId) {
//                R.id.trip -> {
//                        fragment = TripFragment()
//                        loadFragment(fragment)
//                        return@OnNavigationItemSelectedListener true
//                    }
                R.id.home -> {
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.service -> {
                    fragment = ServiceFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }

            }
            return@OnNavigationItemSelectedListener false
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      //  callDefaultTab()
        //initViews()
    }

//    private fun callDefaultTab() {
//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.replace(R.id.frame_container, active, active.javaClass.simpleName)
//            ?.disallowAddToBackStack()
//            //    .addToBackStack(null)
//            ?.commit()
//    }

    fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container, fragment)
        //transaction.addToBackStack(null)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

}