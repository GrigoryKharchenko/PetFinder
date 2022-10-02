package com.example.petfinder.domain.interceptor

interface NetworkRepository {

    suspend fun getNetworkToken():TokenModel


}