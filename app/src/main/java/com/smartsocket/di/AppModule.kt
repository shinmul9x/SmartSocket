package com.smartsocket.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.smartsocket.SmartSocketApp
import com.smartsocket.service.api.ApiManager
import com.smartsocket.service.api.SmartSocketService
import com.smartsocket.utils.DataManager
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
    fun provideSmartSocketService(): SmartSocketService {
        return ApiManager(application as SmartSocketApp).getApiService()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(
            "SMART_SOCKET_SHARED_PREFERENCES",
            Context.MODE_PRIVATE
        )
    }

}