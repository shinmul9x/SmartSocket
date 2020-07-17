package com.smartsocket.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartsocket.R
import com.smartsocket.SmartSocketApp
import com.smartsocket.utils.Constant
import com.smartsocket.view.adapter.HomeAdapter
import com.smartsocket.viewmodel.HomeViewModel
import com.smartsocket.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        initAppBar()
        initComponents()
        initActions()
    }

    private fun initAppBar() {
        setSupportActionBar(toolbar_main)
    }

    private fun initActions() {
        // handle events click item home
        homeViewModel.itemHomeSelectedListener().observe(this, Observer {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra(Constant.HOME_ID_CONSTANT, it)
            startActivity(intent)
        })
    }

    private fun initComponents() {
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        homeAdapter = HomeAdapter(this, ArrayList(), HomeAdapter.ItemHomeListener {
            homeViewModel.itemSelected(it)
        })
        rv_home_list.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2, RecyclerView.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getHomeList((application as SmartSocketApp).dataManager.getToken()!!)
            .observe(this, Observer {
                if (it.isNullOrEmpty()) {
                    Toast.makeText(this, "Check internet or host address", Toast.LENGTH_SHORT).show()
                    return@Observer
                }
                homeAdapter.setHomeList(it)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (application as SmartSocketApp).dataManager.removeToken()
        this.finish()
        return super.onOptionsItemSelected(item)
    }
}
