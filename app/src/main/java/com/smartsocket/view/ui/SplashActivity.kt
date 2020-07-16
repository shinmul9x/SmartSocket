package com.smartsocket.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.smartsocket.R
import com.smartsocket.SmartSocketApp

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val timer = object : CountDownTimer(3000, 3000) {
            override fun onFinish() {
                checkLogin()
            }

            override fun onTick(p0: Long) {
            }

        }
        timer.start()
    }

    private fun checkLogin() {
        val token = (application as SmartSocketApp).dataManager.getToken()
        if (token.isNullOrEmpty()) {
            openLoginActivity()
        } else {
            openMainActivity()
        }
        this.finish()
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))

    }
}