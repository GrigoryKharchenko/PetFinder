package com.example.petfinder.domain.interceptor

import com.example.petfinder.domain.response.Api
import com.example.petfinder.domain.response.ListPetApi
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val listPetApi: ListPetApi
) : NetworkRepository {
    override suspend fun getNetworkToken(): TokenModel =
        listPetApi.sendToken(Api.GRANT_TYPE, Api.CLIENT_ID, Api.CLIENT_SECRET)
}