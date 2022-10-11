package com.example.petfinder.domain.repository

import com.example.petfinder.data.response.TokenResponse

interface NetworkRepository {

    suspend fun getNetworkToken(): TokenResponse


}