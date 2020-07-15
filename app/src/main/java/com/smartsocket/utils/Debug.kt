package com.smartsocket.utils

import android.util.Log
import java.util.regex.Pattern

class Debug {
    private val prefixTag = "SMART_SOCKET"

    private fun getTag(): String {
        val element = Throwable().stackTrace[2] ?: return prefixTag // if null return TAG
        val pkgPatterns = Pattern.compile(".*\\.")
        val className = pkgPatterns.matcher(element.className).replaceAll("")
        val lineNumber = element.lineNumber
        return "$prefixTag|$className($lineNumber)"
    }

    fun d(msg: String) {
        Log.d(getTag(), msg)
    }

}