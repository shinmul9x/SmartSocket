package com.smartsocket.di

import com.smartsocket.view.ui.LoginActivity
import com.smartsocket.view.ui.MainActivity
import com.smartsocket.view.ui.RoomActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    // implement activities use Dagger
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeRoomActivity(): RoomActivity
}