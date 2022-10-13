package com.example.petfinder.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.petfinder.data.response.AnimalResponse
import com.example.petfinder.data.response.PetResponse
import com.example.petfinder.presentation.screen.model.PetModel

fun PetResponse.mapToPetModels(): List<PetModel> =
    this.animalResponses.map(AnimalResponse::mapToPetModel)

fun AnimalResponse.mapToPetModel(): PetModel =
    PetModel(
        id = id,
        photo = photo.firstOrNull()?.mediumPhoto ?: "",
        type = type,
        age = age,
        name = name,
        breeds = breedsResponse.primary,
        gender = gender,
        size = size,
        tags = tags.joinToString()
    )
