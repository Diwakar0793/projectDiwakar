package com.application.scoo.di.modules

import com.application.scoo.data.service.BoomDataService
import com.application.scoo.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class NetworkServiceModule {

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): BoomDataService {
        return retrofit.create(BoomDataService::class.java)
    }
}