package com.smartsocket.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(val sharedPreferences: SharedPreferences) {

    private val tokenTag = "LOGIN_TOKEN"

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(tokenTag, token).apply()
    }

    fun removeToken() {
        sharedPreferences.edit().remove(tokenTag).apply()
    }

    fun getToken() = sharedPreferences.getString(tokenTag, "")
}