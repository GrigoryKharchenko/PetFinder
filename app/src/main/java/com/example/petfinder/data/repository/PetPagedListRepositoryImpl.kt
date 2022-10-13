package com.example.petfinder.data.repository

import com.example.petfinder.data.mapper.mapToPetModels
import com.example.petfinder.data.network.ListPetApi
import com.example.petfinder.domain.preference.PreferenceManager
import com.example.petfinder.domain.repository.PetPagedListRepository
import com.example.petfinder.presentation.screen.model.PetModel
import javax.inject.Inject

class PetPagedListRepositoryImpl @Inject constructor(
    private val listPetApi: ListPetApi,
    private val dataStore: PreferenceManager
) : PetPagedListRepository {
    override suspend fun getListPets(
        page: Int
    ): List<PetModel> {
        return listPetApi.getListPets(
            page,
            dataStore.getTypeAnimal().type?.lowercase(),
            dataStore.getGenderAnimal().gender?.lowercase()
        ).mapToPetModels()
    }
}