package com.smartsocket.service.api

import androidx.lifecycle.MutableLiveData
import com.smartsocket.service.model.LoginToken
import com.smartsocket.utils.Debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartSocketApi @Inject constructor(private val smartSocketService: SmartSocketService) {

    fun login(username: String, password: String): MutableLiveData<String> {
        val data = MutableLiveData<String>()
        smartSocketService.getLoginToken(username).enqueue(object : Callback<LoginToken> {
            override fun onFailure(call: Call<LoginToken>, t: Throwable) {
                data.value = ""
            }

            override fun onResponse(call: Call<LoginToken>, response: Response<LoginToken>) {
                if (response.code() == 200) {
                    data.value = response.body()?.token ?: ""
                } else {
                    data.value = ""
                }
            }
        })
        return data
    }

    init {
        var index = 0
        Debug().d("number of instance smartsocketapi: ${++index}")
    }
}