package com.application.scoo.di.modules

import com.application.scoo.ui.base.BaseFragment
import com.application.scoo.ui.home.*
import com.application.scoo.ui.home.BatteryInfoFragment
import com.application.scoo.ui.home.DashBoardFragment
import com.application.scoo.ui.home.map.LiveTrackingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun bindDashBoardFragment(): DashBoardFragment

    @ContributesAndroidInjector
    abstract fun bindBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun bindLiveTrackingFragment(): LiveTrackingFragment

    @ContributesAndroidInjector
    abstract fun bindBatteryInfoFragment(): BatteryInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeRideStatfragment(): RideStatfragment

    @ContributesAndroidInjector
    abstract fun contributeTripHistoryFragment(): TripHistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeServiceInfo(): ServiceInfo

    @ContributesAndroidInjector
    abstract fun contributeServiceBookFragment(): ServiceBookFragment

    @ContributesAndroidInjector
    abstract fun contributeRoadAssistenceFragment(): RoadAssistenceFragment

    @ContributesAndroidInjector
    abstract fun contributeServiceHistoryFragment(): ServiceHistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeServiceInfoBookDetail(): ServiceInfoBookDetail


}