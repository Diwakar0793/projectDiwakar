package com.application.scoo.ui.home.bottomNavViews.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.scoo.databinding.FragmentPastServiceBinding

class PastServiceFragment : Fragment() {

    private var _pastServiceFragmentBinding: FragmentPastServiceBinding? = null

    private val pastServiceFragmentBinding get() = _pastServiceFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _pastServiceFragmentBinding =
            FragmentPastServiceBinding.inflate(inflater, container, false)


        return pastServiceFragmentBinding.root
    }
}