package com.example.petfinder.presentation.ui.lisstpets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petfinder.domain.response.ListPetApi
import com.example.petfinder.domain.response.PetResponse
import com.example.petfinder.presentation.ui.model.PetModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListPetsViewModel @Inject constructor(
    private val listPetApi: ListPetApi
) : ViewModel() {

    private val _petModelFlow = MutableSharedFlow<List<PetModel>>()
    val petFlow = _petModelFlow.asSharedFlow()

    fun getListPets() {
        viewModelScope.launch {
            runCatching {
                listPetApi.getListPets()
            }.onSuccess { petResponse ->
                _petModelFlow.emit(petResponse.mapToPetModel())
            }.onFailure {
                _petModelFlow.emit(emptyList())
            }
        }
    }
//TODO create new file for ext
    private fun PetResponse.mapToPetModel(): List<PetModel> =
        this.animals.map { animal ->
            PetModel(
                id = animal.id,
                photo = animal.photo.firstOrNull()?.mediumPhoto ?: "",
                type = animal.type,
                age = animal.age,
                name = animal.name,
                breeds = animal.breeds.primary,
                gender = animal.gender,
                size = animal.size,
                tags = animal.tags.joinToString()
            )
        }

    fun findPets(namePet: String) {
        viewModelScope.launch {
            runCatching {
                //TODO убрать вызов запроса
                listPetApi.getListPets()
            }.onSuccess { petResponse ->
                //change to contains
                _petModelFlow.emit(petResponse.mapToPetModel().filter { petModel ->
                    petModel.name == namePet
                })
            }.onFailure {
                _petModelFlow.emit(emptyList())
            }
        }
    }


    init {
        getListPets()
    }
}