package com.example.petfinder.di.module

import com.example.petfinder.domain.datastore.PreferenceManagerImpl
import com.example.petfinder.domain.preference.PreferenceManager
import dagger.Binds
import dagger.Module


@Module
abstract class PreferenceManagerModule {

    @Binds
    abstract fun bindPreferenceManager(dataStore: PreferenceManagerImpl): PreferenceManager
}