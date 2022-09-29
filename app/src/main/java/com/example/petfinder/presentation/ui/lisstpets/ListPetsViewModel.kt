package com.example.petfinder.presentation.ui.lisstpets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petfinder.domain.response.Api
import com.example.petfinder.domain.response.ListPetApi
import com.example.petfinder.domain.response.PetResponse
import com.example.petfinder.presentation.ui.model.PetModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListPetsViewModel @Inject constructor(private val listPetApi: ListPetApi) : ViewModel() {

    private val _photoModelFlow = MutableSharedFlow<List<PetModel>>()
    val photoFlow = _photoModelFlow.asSharedFlow()

    private fun getPhoto() {
        viewModelScope.launch {
            runCatching {
                listPetApi.sendToken(Api.GRANT_TYPE, Api.CLIENT_ID, Api.CLIENT_SECRET)
                listPetApi.getListPets()
            }.onSuccess { photoResponse ->
                _photoModelFlow.emit(photoResponse.mapToPhotoModel())
            }.onFailure {
                _photoModelFlow.emit(emptyList())
            }
        }
    }

    private fun PetResponse.mapToPhotoModel(): List<PetModel> =
        this.animals.map { animal ->
            PetModel(
                id = animal.id,
                photo = animal.photo.firstOrNull()?.smallPhoto ?: "",
                type = animal.type,
                age = animal.age,
                name = animal.name,
                breeds = animal.breeds.breeds,
            )
        }

    init {
        getPhoto()
    }
}