package com.smartsocket.di

import com.smartsocket.SmartSocketApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(application: SmartSocketApp)
}