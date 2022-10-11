package com.example.petfinder.data.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token_type") val type: String,
    @SerializedName("expires_in") val time: Int,
    @SerializedName("access_token") val token: String
)