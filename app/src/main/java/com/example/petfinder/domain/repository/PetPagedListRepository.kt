package com.example.petfinder.domain.repository

import com.example.petfinder.presentation.screen.model.PetModel

interface PetPagedListRepository {
    suspend fun getListPets(
        page: Int
    ): List<PetModel>
}