package com.application.scoo.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.scoo.di.annotations.ApplicationScope
import com.application.scoo.di.annotations.ViewModelKey
import com.application.scoo.login.ChangePasswordViewModel
import com.application.scoo.login.LoginViewModel
import com.application.scoo.login.OTPViewModel
import com.application.scoo.ui.PayRentalViewModel
import com.application.scoo.ui.home.DashboardViewModel
import com.application.scoo.ui.home.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    abstract fun bindViewModelFactory(viewModelFactory: BoomViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(logninViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OTPViewModel::class)
    abstract fun bindotpViewModel(otpViewModel: OTPViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun bindChangePasswordViewModel(changePasswordViewModl: ChangePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModl: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(mapViewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PayRentalViewModel::class)
    abstract fun bindPayRentalViewModel(mapViewModel: PayRentalViewModel): ViewModel
}