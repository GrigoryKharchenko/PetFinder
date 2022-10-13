package com.example.petfinder.domain.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.petfinder.domain.enumeration.GenderAnimal
import com.example.petfinder.domain.enumeration.TypeAnimal
import com.example.petfinder.domain.preference.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class PreferenceManagerImpl @Inject constructor(
    private val context: Context
) : PreferenceManager {

    private val tokenKey = stringPreferencesKey("key_token")
    private val typeKey = stringPreferencesKey("type_token")
    private val genderKey = stringPreferencesKey("gender_key")

    private val token = context.dataStore.data.map { dataStore ->
        dataStore[tokenKey] ?: ""
    }

    private val type: Flow<TypeAnimal> = context.dataStore.data.map { dataStore ->
        dataStore[typeKey]?.let(TypeAnimal::valueOf) ?: TypeAnimal.ANIMAL
    }

    private val gender: Flow<GenderAnimal> = context.dataStore.data.map { dataStore ->
        dataStore[genderKey]?.let(GenderAnimal::valueOf) ?: GenderAnimal.EMPTY
    }

    override suspend fun getToken(): String = token.first()

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { tokenDataStore ->
            tokenDataStore[tokenKey] = token
        }
    }

    override suspend fun getTypeAnimal(): TypeAnimal = type.first()

    override suspend fun saveTypeAnimal(type: TypeAnimal) {
        context.dataStore.edit { typeDataStore ->
            typeDataStore[typeKey] = type.name
        }
    }

    override suspend fun getGenderAnimal(): GenderAnimal = gender.first()

    override suspend fun saveGenderAnimal(gender: GenderAnimal) {
        context.dataStore.edit { genderDataStore ->
            genderDataStore[genderKey] = gender.name
        }
    }
}