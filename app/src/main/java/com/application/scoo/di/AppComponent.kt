package com.application.scoo.di

import android.app.Application
import com.application.scoo.AppBase
import com.application.scoo.di.annotations.ApplicationScope
import com.application.scoo.di.modules.ActivityBuilderModule
import com.application.scoo.di.modules.AppModule
import com.application.scoo.di.modules.FragmentBuildersModule
import com.application.scoo.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilderModule::class, FragmentBuildersModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(mApplication: AppBase)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}