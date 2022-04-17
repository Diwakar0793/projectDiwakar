package com.application.scoo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentServiceinfoBookDetailsBinding
import com.application.scoo.ui.base.BaseFragment

class ServiceInfoBookDetail:BaseFragment(), View.OnClickListener {

    private var _servicebookdetailsBinding: FragmentServiceinfoBookDetailsBinding? = null
    private val servicebookdetailsBinding get() = _servicebookdetailsBinding!!

    companion object {
        fun newInstance() =
            ServiceInfoBookDetail()
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

        _servicebookdetailsBinding =
            FragmentServiceinfoBookDetailsBinding.inflate(inflater, container, false)
        return servicebookdetailsBinding.root
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
        servicebookdetailsBinding.backbtn.setOnClickListener {
          //  Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.popBackStack() }
    }

    override fun onClick(v: View?) {
        val fragment: Fragment
        when (v?.id) {

        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}