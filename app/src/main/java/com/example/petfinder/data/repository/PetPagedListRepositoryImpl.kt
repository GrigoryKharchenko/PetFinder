package com.example.petfinder.data.repository

import com.example.petfinder.data.mapper.mapToPetModels
import com.example.petfinder.data.network.ListPetApi
import com.example.petfinder.domain.repository.PetPagedListRepository
import com.example.petfinder.presentation.screen.model.PetModel
import javax.inject.Inject

class PetPagedListRepositoryImpl @Inject constructor(
    private val listPetApi: ListPetApi
) : PetPagedListRepository {
    override suspend fun getListPets(
        page: Int,
        type: String?,
        gender: String?
    ): List<PetModel> {
        return listPetApi.getListPets(page, type, gender).mapToPetModels()
    }
}