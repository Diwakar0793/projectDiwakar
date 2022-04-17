package com.application.scoo.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentRoadsideAssistenceBinding
import com.application.scoo.ui.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import dagger.android.support.AndroidSupportInjection


class RoadSideAssistanceFragment : BaseFragment(), View.OnClickListener{

    private var _roadsideAssistaneBinding : FragmentRoadsideAssistenceBinding? = null
    private val roadsideAssistaneBinding get() = _roadsideAssistaneBinding!!

    var selectedOption: Int = 0
    private var serviceCheckedRadioButtonValue: String = ""
    private lateinit var mMap: GoogleMap

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

        _roadsideAssistaneBinding =
            FragmentRoadsideAssistenceBinding.inflate(inflater, container, false)
        initData()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        return roadsideAssistaneBinding.root

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }



    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun initData() {
        roadsideAssistaneBinding.backBtn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.backBtn -> {
                activity?.supportFragmentManager?.popBackStack()
            }

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

    companion object {
        fun newInstance() =
            RoadSideAssistanceFragment()
    }



}
