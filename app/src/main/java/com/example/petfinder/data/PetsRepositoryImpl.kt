package com.example.petfinder.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.petfinder.domain.PetsRepository
import com.example.petfinder.domain.response.Animal
import com.example.petfinder.domain.response.Api
import com.example.petfinder.domain.response.ListPetApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetsRepositoryImpl @Inject constructor(
    private val api: ListPetApi
) : PetsRepository {

    override fun getPets(): Flow<PagingData<Animal>> =
        Pager(
            config = PagingConfig(
                pageSize = Api.MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PetsFinderPageSource(api)
            }
        ).flow

}