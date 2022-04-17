package com.application.scoo.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.application.scoo.di.modules.Injectable
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


open class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: BaseViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        activity?.let {
            viewModel = ViewModelProvider(it, viewModelFactory)
                .get(BaseViewModel::class.java)
        }
        super.onAttach(context)
    }

}