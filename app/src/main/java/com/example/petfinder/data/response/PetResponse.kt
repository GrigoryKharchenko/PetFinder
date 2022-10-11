package com.example.petfinder.data.response

import androidx.paging.PagingData
import com.google.gson.annotations.SerializedName

data class PetResponse(
    @SerializedName("animals") val animalResponses: List<AnimalResponse>
)

data class AnimalResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("breeds") val breedsResponse: BreedsResponse,
    @SerializedName("age") val age: String,
    @SerializedName("name") val name: String,
    @SerializedName("photos") val photo: List<PhotosResponse>,
    @SerializedName("gender") val gender: String,
    @SerializedName("size") val size: String,
    @SerializedName("tags") val tags: List<String>

)

data class BreedsResponse(
    @SerializedName("primary") val primary: String,
)

data class PhotosResponse(
    @SerializedName("small") val smallPhoto: String,
    @SerializedName("medium") val mediumPhoto: String,
    @SerializedName("large") val largePhoto: String
)
