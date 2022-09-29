package com.example.petfinder.domain.interceptor

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("token_type") val type: String,
    @SerializedName("expires_in") val time: Int,
    @SerializedName("access_token") val token: String
)