package com.example.petfinder.domain

import androidx.paging.PagingData
import com.example.petfinder.domain.response.Animal
import kotlinx.coroutines.flow.Flow

interface PetsRepository {
    fun getPets(): Flow<PagingData<Animal>>
}