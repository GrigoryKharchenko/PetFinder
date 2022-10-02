package com.example.petfinder.di.module

import androidx.lifecycle.ViewModel
import com.example.petfinder.di.ViewModelKey
import com.example.petfinder.presentation.ui.lisstpets.ListPetsFragment
import com.example.petfinder.presentation.ui.lisstpets.ListPetsViewModel
import com.example.petfinder.presentation.ui.profilepet.ProfilePetFragment
import com.example.petfinder.presentation.ui.profilepet.ProfilePetViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindListPetsFragment(): ListPetsFragment

    @ContributesAndroidInjector
    fun bindProfilePetFragment(): ProfilePetFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListPetsViewModel::class)
    fun bindListPetsViewModel(viewModel: ListPetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfilePetViewModel::class)
    fun bindProfilePetViewModel(viewModel: ProfilePetViewModel): ViewModel
}