package com.smartsocket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartsocket.service.api.SmartSocketApi
import com.smartsocket.service.model.Room
import javax.inject.Inject

class RoomViewModel @Inject constructor(
    application: Application,
    private val smartSocketApi: SmartSocketApi
) : AndroidViewModel(application) {
    private val itemRoomSelectedListener = MutableLiveData<Int>()

    fun itemSelected(room: Room?) {
        itemRoomSelectedListener.value = room?.id
    }

    fun itemRoomSelectedListener(): LiveData<Int> {
        return itemRoomSelectedListener
    }

    fun getRoomList(token: String, homeID: Int): LiveData<ArrayList<Room?>> {
        return smartSocketApi.getRoomList(token, homeID)
    }
}