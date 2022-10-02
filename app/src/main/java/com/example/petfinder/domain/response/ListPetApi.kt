package com.example.petfinder.domain.response

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ListPetApi {
    @POST("https://api.petfinder.com/v2/oauth2/token")
    @FormUrlEncoded
    suspend fun sendToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    )

    @GET("v2/animals?type=dog&page=40")
    suspend fun getListPets(): PetResponse
}

object Api {
    const val BASE_URL = "https://api.petfinder.com"
    const val CLIENT_ID = "HHNgfYe55nQ0s2QNwWaLeOJ20tqNyXiRkdaz5PKboJ37hBk7EG"
    const val CLIENT_SECRET = "UDqV457aVBjeIgwEolvDTC5jMFVMt7e7y6QgxrxA"
    const val GRANT_TYPE = "client_credentials"
}
