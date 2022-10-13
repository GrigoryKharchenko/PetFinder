package com.example.petfinder.presentation.screen.lisstpets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.petfinder.data.network.PetsFinderPageSource
import com.example.petfinder.domain.enumeration.GenderAnimal
import com.example.petfinder.domain.enumeration.TypeAnimal
import com.example.petfinder.domain.preference.PreferenceManager
import com.example.petfinder.presentation.screen.model.PetModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ListPetsViewModel @Inject constructor(
    private val petsFinderPageSource: Provider<PetsFinderPageSource>,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _petTypeFlow = MutableSharedFlow<TypeAnimal>(replay = 1)
    val petTypeFlow = _petTypeFlow.asSharedFlow()

    private val _petGenderFlow = MutableSharedFlow<GenderAnimal>(replay = 1)
    val petGenderFlow = _petGenderFlow.asSharedFlow()

    val pets: StateFlow<PagingData<PetModel>> =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { petsFinderPageSource.get() })
            .flow
            .cachedIn(viewModelScope)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily, PagingData.empty()
            )

    init {
        getPetType()
        getPetGender()
    }

    fun changePetGender(genderAnimal: GenderAnimal) {
        viewModelScope.launch {
            preferenceManager.saveGenderAnimal(genderAnimal)
            _petGenderFlow.emit(genderAnimal)
        }
    }

    fun changePetType(typeAnimal: TypeAnimal) {
        viewModelScope.launch {
            preferenceManager.saveTypeAnimal(typeAnimal)
            _petTypeFlow.emit(typeAnimal)
        }
    }

    private fun getPetGender() {
        viewModelScope.launch {
            _petGenderFlow.emit(preferenceManager.getGenderAnimal())
        }
    }

    private fun getPetType() {
        viewModelScope.launch {
            _petTypeFlow.emit(preferenceManager.getTypeAnimal())
        }
    }

}


