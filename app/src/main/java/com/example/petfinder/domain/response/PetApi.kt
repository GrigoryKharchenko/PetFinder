package com.example.petfinder.domain.response

import androidx.annotation.IntRange
import com.example.petfinder.domain.interceptor.TokenModel
import com.example.petfinder.domain.response.Api.DEFAULT_PAGE_SIZE
import com.example.petfinder.domain.response.Api.MAX_PAGE_SIZE

import retrofit2.http.*

interface ListPetApi {
    @POST("https://api.petfinder.com/v2/oauth2/token")
    @FormUrlEncoded
    suspend fun sendToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): TokenModel

    @GET("v2/animals")
    suspend fun getListPets(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) limit: Int = DEFAULT_PAGE_SIZE,
    ): PetResponse
}

object Api {
    const val BASE_URL = "https://api.petfinder.com"
    const val CLIENT_ID = "HHNgfYe55nQ0s2QNwWaLeOJ20tqNyXiRkdaz5PKboJ37hBk7EG"
    const val CLIENT_SECRET = "UDqV457aVBjeIgwEolvDTC5jMFVMt7e7y6QgxrxA"
    const val GRANT_TYPE = "client_credentials"
    const val DEFAULT_PAGE_SIZE = 20
    const val MAX_PAGE_SIZE = 100
}
