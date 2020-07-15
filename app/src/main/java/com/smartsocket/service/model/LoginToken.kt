package com.smartsocket.service.model

import com.google.gson.annotations.SerializedName

data class LoginToken(
    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("token")
    val token: String? = null
)