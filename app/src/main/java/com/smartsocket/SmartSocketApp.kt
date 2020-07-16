package com.smartsocket

import android.app.Activity
import android.app.Application
import com.smartsocket.di.AppModule
import com.smartsocket.di.DaggerAppComponent
import com.smartsocket.utils.DataManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartSocketApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dataManager: DataManager

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }
}