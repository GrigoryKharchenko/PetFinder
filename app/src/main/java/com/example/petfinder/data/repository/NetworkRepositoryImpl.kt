package com.example.petfinder.data.repository

import com.example.petfinder.data.network.Api
import com.example.petfinder.data.network.ListPetApi
import com.example.petfinder.data.response.TokenResponse
import com.example.petfinder.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val listPetApi: ListPetApi
) : NetworkRepository {
    override suspend fun getNetworkToken(): TokenResponse =
        listPetApi.sendToken(Api.GRANT_TYPE, Api.CLIENT_ID, Api.CLIENT_SECRET)
}