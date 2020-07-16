package com.smartsocket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartsocket.service.api.SmartSocketApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginViewModel @Inject constructor(
    application: Application,
    private val smartSocketApi: SmartSocketApi
) : AndroidViewModel(application) {

    fun getLoginToken(username: String, password: String): LiveData<String> {
        return smartSocketApi.login(username, password)
    }
}