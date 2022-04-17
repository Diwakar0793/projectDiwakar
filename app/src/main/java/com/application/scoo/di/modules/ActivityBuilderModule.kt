package com.application.scoo.di.modules

import com.application.scoo.CurrentPlan
import com.application.scoo.di.annotations.ActivityScope
import com.application.scoo.login.ChangePasswordActivity
import com.application.scoo.login.OTPActivity
import com.application.scoo.login.LoginActivity
import com.application.scoo.ui.PayRental
import com.application.scoo.ui.Profile
import com.application.scoo.ui.base.BaseActivity
import com.application.scoo.ui.home.MenuActivity
import com.application.scoo.ui.home.map.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {


    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindLogineActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMenuActivity(): MenuActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindOTPActivity(): OTPActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMapsActivity(): MapsActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindProfile(): Profile

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindChangePasswordActivity(): ChangePasswordActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindCurrentPlan(): CurrentPlan

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindPayRental(): PayRental

}



