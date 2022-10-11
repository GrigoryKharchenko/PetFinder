package com.example.petfinder.di.module

import androidx.paging.PagingSource
import com.example.petfinder.data.network.PetsFinderPageSource
import com.example.petfinder.presentation.screen.model.PetModel
import dagger.Binds
import dagger.Module

@Module
interface PagingModule {

    @Binds
    fun bindPetsFinderPageSource(petsFinderPageSource: PetsFinderPageSource): PagingSource<Int, PetModel>
}