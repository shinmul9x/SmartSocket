package com.smartsocket.service.api

import com.smartsocket.SmartSocketApp
import com.smartsocket.utils.Debug
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class ApiManager constructor(application: SmartSocketApp) {
    private val url = "http://${application.dataManager.getHostAddress()}"

    fun getApiService(): SmartSocketService {
        Debug().d("url: $url")
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmartSocketService::class.java)
    }
}