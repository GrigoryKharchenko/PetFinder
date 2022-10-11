package com.example.petfinder.data.datastore

interface TokenDataStore {

    suspend fun getToken(): String
    suspend fun saveToken(token:String)
}