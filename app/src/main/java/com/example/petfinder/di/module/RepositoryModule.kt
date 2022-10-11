package com.example.petfinder.di.module

import com.example.petfinder.data.repository.NetworkRepositoryImpl
import com.example.petfinder.data.repository.PetPagedListRepositoryImpl
import com.example.petfinder.domain.repository.NetworkRepository
import com.example.petfinder.domain.repository.PetPagedListRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindNetworkRepository(repository: NetworkRepositoryImpl): NetworkRepository

    @Binds
    fun bindPetPagedListRepository(repositoryImpl: PetPagedListRepositoryImpl): PetPagedListRepository
}