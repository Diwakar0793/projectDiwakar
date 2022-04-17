package com.application.scoo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentHomeBaseDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage: Fragment() {

private var _homePageBinding: FragmentHomeBaseDashboardBinding? = null
private val homePageBinding get() = _homePageBinding!!

//  val navController = findNavController(R.id.frame_container)
private val fragment1: Fragment = DashBoardFragment()
private var active = fragment1
lateinit var navigation: BottomNavigationView


override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {

    _homePageBinding =
        FragmentHomeBaseDashboardBinding.inflate(inflater, container, false)

    val fragment: Fragment
    fragment = DashBoardFragment()
    loadFragment(fragment)

    return homePageBinding.root
}

/*private fun initViews() {
    navigation = homePageBinding.bottomNavigation
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    navigation.clearAnimation()

}*/



/*private val mOnNavigationItemSelectedListener =
    BottomNavigationView.OnNavigationItemSelectedListener {
        val fragment: Fragment
        when (it.itemId) {
            *//*R.id.trip -> {
                fragment = ButtonOneFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }*//*


        }
        return@OnNavigationItemSelectedListener false
    }*/
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    callDefaultTab()
    //initViews()
}

private fun callDefaultTab() {
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(R.id.fragment_container, active, active.javaClass.simpleName)
        //?.disallowAddToBackStack()
        ?.addToBackStack("1")
        ?.commit()
}

fun loadFragment(fragment: Fragment) {
    // load fragment
    val transaction = activity?.supportFragmentManager?.beginTransaction()
    transaction?.replace(R.id.fragment_container, fragment)
    //transaction.addToBackStack(null)
    transaction?.disallowAddToBackStack()
    transaction?.commit()
}

    companion object {
        fun newInstance() = HomePage()
    }
}

