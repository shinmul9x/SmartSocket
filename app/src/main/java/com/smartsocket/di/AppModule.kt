package com.smartsocket.di

import android.app.Application
import com.smartsocket.service.api.ApiManager
import com.smartsocket.service.api.SmartSocketService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideSmartSocketService() : SmartSocketService {
        return ApiManager().getApiService()
    }
}