package com.smartsocket.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smartsocket.viewmodel.HomeViewModel
import com.smartsocket.viewmodel.LoginViewModel
import com.smartsocket.viewmodel.RoomViewModel
import com.smartsocket.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // implement function get view models mapping with factory
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomViewModel::class)
    internal abstract fun roomViewModel(roomViewModel: RoomViewModel): ViewModel
}