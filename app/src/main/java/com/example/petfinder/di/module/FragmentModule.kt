package com.example.petfinder.di.module

import androidx.lifecycle.ViewModel
import com.example.petfinder.di.ViewModelKey
import com.example.petfinder.presentation.ui.lisstpets.ListPetsFragment
import com.example.petfinder.presentation.ui.lisstpets.ListPetsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindListPetsFragment(): ListPetsFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListPetsViewModel::class)
    fun bindListPetsViewModel(viewModel: ListPetsViewModel): ViewModel
}