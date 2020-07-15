package com.smartsocket.service.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class ApiManager {
    private val url = "http://192.168.2.101:5000"

    fun getApiService(): SmartSocketService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmartSocketService::class.java)
    }
}