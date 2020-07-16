package com.smartsocket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartsocket.service.api.SmartSocketApi
import com.smartsocket.service.model.Home
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: Application,
    private val smartSocketApi: SmartSocketApi
) : AndroidViewModel(application) {
    private val itemHomeSelectedListener = MutableLiveData<Int>()

    fun getHomeList(token: String): LiveData<ArrayList<Home?>> {
        return smartSocketApi.getHomeList(token)
    }

    fun itemHomeSelectedListener(): LiveData<Int> {
        return itemHomeSelectedListener
    }

    fun itemSelected(homeId: Int) {
        itemHomeSelectedListener.value = homeId
    }
}