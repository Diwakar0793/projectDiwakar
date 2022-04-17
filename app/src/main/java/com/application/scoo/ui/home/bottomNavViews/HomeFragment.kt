package com.application.scoo.ui.home.bottomNavViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.application.scoo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _homeFragmentBinding : FragmentHomeBinding? = null

    private val homeFragmentBinding get() = _homeFragmentBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _homeFragmentBinding =
            FragmentHomeBinding.inflate(inflater, container, false)


      return homeFragmentBinding.root
    }
}