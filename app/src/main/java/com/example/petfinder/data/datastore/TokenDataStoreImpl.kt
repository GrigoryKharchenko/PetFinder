package com.example.petfinder.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class TokenDataStoreImpl @Inject constructor(
    private val context: Context
) : TokenDataStore {

    private val tokenKey = stringPreferencesKey("key_token")

    private val token = context.dataStore.data.map { dataStore ->
        dataStore[tokenKey] ?: ""
    }

    override suspend fun getToken(): String = token.first()

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { tokenDataStore ->
            tokenDataStore[tokenKey] = token
        }
    }
}