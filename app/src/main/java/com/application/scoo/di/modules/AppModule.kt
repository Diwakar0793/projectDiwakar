package com.application.scoo.di.modules

import android.app.Application
import android.content.Context
import com.application.scoo.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkServiceModule::class])
object AppModule {
    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideContext(application: Application): Context = application
}