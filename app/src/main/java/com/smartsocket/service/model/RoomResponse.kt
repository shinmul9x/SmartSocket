package com.smartsocket.service.model

import com.google.gson.annotations.SerializedName

data class RoomResponse(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("room")
    val room: List<Room?>? = null,

    @field:SerializedName("message")
    val message: String? = null
)