package com.example.petfinder.di.module

import com.example.petfinder.domain.interceptor.NetworkRepository
import com.example.petfinder.domain.interceptor.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}