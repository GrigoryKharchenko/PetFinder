package com.example.petfinder.di.module

import com.example.petfinder.data.datastore.TokenDataStore
import com.example.petfinder.data.datastore.TokenDataStoreImpl
import dagger.Binds
import dagger.Module


@Module
abstract class DataStoreModule {

    @Binds
    abstract fun bindTokenDataStore(dataStore: TokenDataStoreImpl): TokenDataStore
}