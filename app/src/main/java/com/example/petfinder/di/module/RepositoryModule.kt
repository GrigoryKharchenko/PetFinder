package com.example.petfinder.di.module

import com.example.petfinder.data.PetsRepositoryImpl
import com.example.petfinder.domain.PetsRepository
import com.example.petfinder.domain.interceptor.NetworkRepository
import com.example.petfinder.domain.interceptor.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindNetworkRepository(repository: NetworkRepositoryImpl): NetworkRepository

    @Binds
    fun bindPetsRepository(repository: PetsRepositoryImpl): PetsRepository
}