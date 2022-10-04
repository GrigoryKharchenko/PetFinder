package com.example.petfinder.presentation.screen.lisstpets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petfinder.domain.response.ListPetApi
import com.example.petfinder.extensions.mapToPetModels
import com.example.petfinder.presentation.screen.model.PetModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListPetsViewModel @Inject constructor(
    private val listPetApi: ListPetApi,
) : ViewModel() {

    private var initPets = emptyList<PetModel>()

    private val _petModelFlow = MutableSharedFlow<List<PetModel>>(replay = 1)
    val petFlow = _petModelFlow.asSharedFlow()

    init {
        getListPets()
    }

    fun getListPets() {
        viewModelScope.launch {
            runCatching {
                listPetApi.getListPets()
            }.onSuccess { petResponse ->
                val mapToPetModel = petResponse.mapToPetModels()
                initPets = mapToPetModel
                _petModelFlow.emit(mapToPetModel)
            }.onFailure {
                _petModelFlow.emit(emptyList())
            }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            if (query != "") {
                val newPets = initPets.filter {
                    it.name.contains(query)
                }
                _petModelFlow.emit(newPets)
            } else {
                _petModelFlow.emit(initPets)
            }
        }
    }
}