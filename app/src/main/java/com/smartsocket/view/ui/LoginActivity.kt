package com.smartsocket.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartsocket.R
import com.smartsocket.SmartSocketApp
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
        initAppBar()
        initActions()
    }

    private fun initAppBar() {
        setSupportActionBar(toolbar_login)
    }

    private fun initActions() {
        btn_login.setOnClickListener {
            val loginViewModel =
                ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            loginViewModel.getLoginToken(username, password).observe(this, Observer {
                if (it.isNullOrEmpty()) {
                    Toast.makeText(this, "Check internet or host address", Toast.LENGTH_SHORT).show()
                    return@Observer
                } else if (it == "") {
                    Toast.makeText(this, "Login fail! Try again.", Toast.LENGTH_SHORT).show()
                    return@Observer
                }
                (application as SmartSocketApp).dataManager.saveToken(it)
                openMainActivity()
                this.finish()
            })
        }
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this@LoginActivity, SettingActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}