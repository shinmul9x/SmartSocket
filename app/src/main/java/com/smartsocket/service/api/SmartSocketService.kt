package com.smartsocket.service.api

import com.smartsocket.service.model.LoginToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SmartSocketService {
    @POST("/api/login")
    @FormUrlEncoded
    fun getLoginToken(
        @Field("username") username: String
    ): Call<LoginToken>
}