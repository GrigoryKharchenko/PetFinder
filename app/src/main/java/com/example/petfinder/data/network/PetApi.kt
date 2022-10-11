package com.example.petfinder.data.network

import androidx.annotation.IntRange
import com.example.petfinder.data.network.Api.FIRST_PAGE
import com.example.petfinder.data.response.PetResponse
import com.example.petfinder.data.response.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ListPetApi {
    @POST("https://api.petfinder.com/v2/oauth2/token")
    @FormUrlEncoded
    suspend fun sendToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): TokenResponse

    @GET("v2/animals")
    suspend fun getListPets(
        @Query("page") @IntRange(from = 1) page: Int = FIRST_PAGE,
        @Query("type") typeAnimal: String?,
        @Query("gender") genderAnimal: String?
    ): PetResponse
}

object Api {
    const val BASE_URL = "https://api.petfinder.com"
    const val CLIENT_ID = "HHNgfYe55nQ0s2QNwWaLeOJ20tqNyXiRkdaz5PKboJ37hBk7EG"
    const val CLIENT_SECRET = "UDqV457aVBjeIgwEolvDTC5jMFVMt7e7y6QgxrxA"
    const val GRANT_TYPE = "client_credentials"
    const val FIRST_PAGE = 1
}
