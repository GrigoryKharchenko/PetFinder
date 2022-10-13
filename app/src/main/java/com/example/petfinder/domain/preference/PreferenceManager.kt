package com.example.petfinder.domain.preference

import com.example.petfinder.domain.enumeration.GenderAnimal
import com.example.petfinder.domain.enumeration.TypeAnimal

interface PreferenceManager {

    suspend fun getToken(): String
    suspend fun saveToken(token: String)

    suspend fun getTypeAnimal(): TypeAnimal
    suspend fun saveTypeAnimal(type: TypeAnimal)

    suspend fun getGenderAnimal(): GenderAnimal
    suspend fun saveGenderAnimal(gender: GenderAnimal)
}