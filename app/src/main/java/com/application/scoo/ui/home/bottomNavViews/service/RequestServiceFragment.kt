package com.application.scoo.ui.home.bottomNavViews.service

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.application.scoo.R
import com.application.scoo.databinding.FragmentRequestServiceBinding

class RequestServiceFragment : Fragment() {

    private var _requestServiceFragmentBinding: FragmentRequestServiceBinding? = null

    private val requestServiceFragmentBinding get() = _requestServiceFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _requestServiceFragmentBinding =
            FragmentRequestServiceBinding.inflate(inflater, container, false)


        return requestServiceFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDropdown()
        initViews()
    }

    private fun initViews() {
        requestServiceFragmentBinding.customerCareNumber.paintFlags =
            requestServiceFragmentBinding.customerCareNumber.paintFlags.or(Paint.UNDERLINE_TEXT_FLAG)
    }

    private fun initDropdown() {
        val type = arrayOf("Issue 1", "Issue 2", "Issue 3")

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_popup_item,
            type
        )

        val editTextFilledExposedDropdown = requestServiceFragmentBinding.chooseIssueDropdown
        editTextFilledExposedDropdown.setAdapter(adapter)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            editTextFilledExposedDropdown.setOnDismissListener { editTextFilledExposedDropdown.clearFocus() }
        }
    }
}