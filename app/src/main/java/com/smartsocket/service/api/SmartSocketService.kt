package com.smartsocket.service.api

import com.smartsocket.service.model.HomeResponse
import com.smartsocket.service.model.LoginToken
import retrofit2.Call
import retrofit2.http.*

interface SmartSocketService {
    @POST("/api/login")
    @FormUrlEncoded
    fun getLoginToken(
        @Field("username") username: String
    ): Call<LoginToken>

    @GET("api/get-home")
    fun getHomeList(
        @Header("x-access-token") token: String
    ): Call<HomeResponse>
}