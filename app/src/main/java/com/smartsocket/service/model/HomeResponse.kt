package com.smartsocket.service.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("home")
    val home: List<Home?>? = null,

    @field:SerializedName("message")
    val message: String? = null
)