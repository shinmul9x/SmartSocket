package com.smartsocket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartsocket.service.api.SmartSocketApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var smartSocketApi: SmartSocketApi

    fun getLoginToken(username: String, password: String): LiveData<String> {
        return smartSocketApi.login(username, password)
    }
}