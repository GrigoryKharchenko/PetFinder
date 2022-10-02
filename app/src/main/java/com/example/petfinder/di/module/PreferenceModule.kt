package com.example.petfinder.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.petfinder.domain.interceptor.AuthorizationPreference
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    fun providePermissionPreference(sharedPreference: SharedPreferences): AuthorizationPreference =
        AuthorizationPreference(sharedPreference)
}