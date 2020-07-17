package com.smartsocket.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smartsocket.R
import com.smartsocket.SmartSocketApp
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initAppBar()
        initComponents()
        initAction()
    }

    private fun initAction() {
        btn_submit.setOnClickListener {
            val hostAddress = et_host_address.text.toString()
            (application as SmartSocketApp).dataManager.saveHostAddress(hostAddress)
            this.finish()
        }
    }

    private fun initComponents() {
        val hostAddress = (application as SmartSocketApp).dataManager.getHostAddress()
        et_host_address.append(hostAddress)
    }

    private fun initAppBar() {
        setSupportActionBar(toolbar_setting)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}