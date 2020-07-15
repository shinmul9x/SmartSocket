package com.smartsocket.di

import com.smartsocket.view.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    // implement activities use Dagger
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}