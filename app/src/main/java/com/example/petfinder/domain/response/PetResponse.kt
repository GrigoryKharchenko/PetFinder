package com.example.petfinder.domain.response

import com.google.gson.annotations.SerializedName

data class PetResponse(
    @SerializedName("animals") val animals: List<Animal>
)

data class Animal(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("breeds") val breeds: Breeds,
    @SerializedName("age") val age: String,
    @SerializedName("name") val name: String,
    @SerializedName("photos") val photo: List<Photos>,
    @SerializedName("gender") val gender: String,
    @SerializedName("size") val size: String,
    @SerializedName("tags") val tags: List<String>

)

data class Breeds(
    @SerializedName("primary") val primary: String,
)

data class Photos(
    @SerializedName("small") val smallPhoto: String,
    @SerializedName("medium") val mediumPhoto:String,
    @SerializedName("large") val largePhoto:String
)
