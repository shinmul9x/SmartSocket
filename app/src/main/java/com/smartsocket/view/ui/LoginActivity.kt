package com.smartsocket.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartsocket.R
import com.smartsocket.viewmodel.LoginViewModel
import com.smartsocket.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AndroidInjection.inject(this)
        initActions()
    }

    private fun initActions() {
        btn_login.setOnClickListener {
            val loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            loginViewModel.getLoginToken(username, password).observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        }
    }
}