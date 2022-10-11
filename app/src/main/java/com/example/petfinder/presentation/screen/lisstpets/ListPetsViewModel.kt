package com.example.petfinder.presentation.screen.lisstpets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.petfinder.data.network.PetsFinderPageSource
import com.example.petfinder.presentation.screen.model.PetModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

class ListPetsViewModel @Inject constructor(
    private val petsFinderPageSource: Provider<PetsFinderPageSource>
) : ViewModel() {

    val pets: StateFlow<PagingData<PetModel>> =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { petsFinderPageSource.get() })
            .flow
            .cachedIn(viewModelScope)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily, PagingData.empty()
            )
}


