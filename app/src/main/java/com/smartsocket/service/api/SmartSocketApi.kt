package com.smartsocket.service.api

import androidx.lifecycle.MutableLiveData
import com.smartsocket.service.model.*
import com.smartsocket.utils.Debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartSocketApi @Inject constructor(private val smartSocketService: SmartSocketService) {
    init {
        var index = 0
        Debug().d("number of instance smartsocketapi: ${++index}")
    }

    fun login(username: String, password: String): MutableLiveData<String> {
        val data = MutableLiveData<String>()
        smartSocketService.getLoginToken(username).enqueue(object : Callback<LoginToken> {
            override fun onFailure(call: Call<LoginToken>, t: Throwable) {
                data.value = null
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

    fun getHomeList(token: String): MutableLiveData<ArrayList<Home?>> {
        val data = MutableLiveData<ArrayList<Home?>>()
        smartSocketService.getHomeList(token).enqueue(object : Callback<HomeResponse> {
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                when {
                    response.code() == 200 -> {
                        data.value = response.body()?.home?.let { ArrayList(it) }
                        Debug().d("number of home: ${response.body()?.home?.size}")
                    }
                    response.body()?.message == "miss token" -> {
                        data.value = null
                    }
                    else -> {
                        data.value = ArrayList()
                    }
                }
            }

        })
        return data
    }

    fun getRoomList(token: String, homeId: Int): MutableLiveData<ArrayList<Room?>> {
        val data = MutableLiveData<ArrayList<Room?>>()
        smartSocketService.getRoomList(token, homeId).enqueue(object : Callback<RoomResponse> {
            override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<RoomResponse>, response: Response<RoomResponse>) {
                when {
                    response.code() == 200 -> {
                        data.value = response.body()?.room?.let { ArrayList(it) }
                    }
                    response.body()?.message == "miss token" -> {
                        data.value = null
                    }
                    else -> {
                        data.value = ArrayList()
                    }
                }
            }
        })
        return data
    }
}