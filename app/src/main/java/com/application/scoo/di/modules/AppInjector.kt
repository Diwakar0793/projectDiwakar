package com.application.scoo.di.modules

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.application.scoo.AppBase
import dagger.android.AndroidInjection
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection

object AppInjector {
    fun init(mApplication: AppBase) {
        mApplication.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            @SuppressLint("SourceLockedOrientationActivity")
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                // do nothing
            }

            override fun onActivityResumed(activity: Activity) {
                // do nothing
            }

            override fun onActivityPaused(activity: Activity) {
                // do nothing
            }

            override fun onActivityStopped(activity: Activity) {
                // do nothing
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                // do nothing
            }

            override fun onActivityDestroyed(activity: Activity) {
                // do nothing
            }
        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasActivityInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
        }
    }
}