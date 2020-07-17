package com.smartsocket.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartsocket.R
import com.smartsocket.SmartSocketApp
import com.smartsocket.utils.Constant
import com.smartsocket.view.adapter.RoomAdapter
import com.smartsocket.viewmodel.RoomViewModel
import com.smartsocket.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.actvity_room.*
import javax.inject.Inject

class RoomActivity: AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var token: String
    private  var homeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_room)
        AndroidInjection.inject(this)
        initAppBar()
        initComponents()
        initActions()
    }

    private fun initAppBar() {
        setSupportActionBar(toolbar_room)
        val homeName = intent.getIntExtra(Constant.HOME_ID_CONSTANT, 0)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = "Home: " + if (homeName == 0) "not home" else "$homeName"
        }
    }

    private fun initComponents() {
        token = (application as SmartSocketApp).dataManager.getToken().toString()
        homeId = intent.getIntExtra(Constant.HOME_ID_CONSTANT, 0)
        roomViewModel = ViewModelProvider(this, viewModelFactory)[RoomViewModel::class.java]
        roomAdapter = RoomAdapter(this, ArrayList(), RoomAdapter.ItemRoomListener {
            roomViewModel.itemSelected(it)
        })
        rv_room_list.apply {
            layoutManager = LinearLayoutManager(this@RoomActivity, RecyclerView.VERTICAL, false)
            adapter = roomAdapter
        }
    }

    private fun initActions() {
        roomViewModel.itemRoomSelectedListener().observe(this, Observer {
            if (it == null) return@Observer
            Toast.makeText(this, "room ID: $it", Toast.LENGTH_SHORT).show()
            // implement handle event item clicked
        })
    }

    override fun onResume() {
        super.onResume()
        roomViewModel.getRoomList(token, homeId).observe(this, Observer {
            roomAdapter.setRoomList(it)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}