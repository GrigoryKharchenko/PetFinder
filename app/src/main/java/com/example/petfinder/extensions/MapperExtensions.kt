package com.example.petfinder.extensions

import com.example.petfinder.domain.response.Animal
import com.example.petfinder.domain.response.PetResponse
import com.example.petfinder.presentation.screen.model.PetModel

fun PetResponse.mapToPetModels(): List<PetModel> =
    this.animals.map(Animal::mapToPetModel)

fun Animal.mapToPetModel(): PetModel =
    PetModel(
        id = id,
        photo = photo.firstOrNull()?.smallPhoto ?: "",
        type = type,
        age = age,
        name = name,
        breeds = breeds.primary,
        gender = gender,
        size = size,
        tags = tags.joinToString()
    )
