package com.application.scoo

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.application.scoo.common.SharedPreference
import com.application.scoo.di.AppComponent
import com.application.scoo.di.DaggerAppComponent
import com.application.scoo.di.modules.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

val sPreference: SharedPreference by lazy {
    AppBase.sharedPreference!!
}

open class AppBase : MultiDexApplication(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: AppComponent

    companion object {
        var sharedPreference: SharedPreference? = null
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreference = SharedPreference(context = this)
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        AppInjector.init(this)
    }
}