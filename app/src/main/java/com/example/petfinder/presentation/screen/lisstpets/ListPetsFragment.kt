package com.example.petfinder.presentation.screen.lisstpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentListPetsBinding
import com.example.petfinder.domain.enumeration.GenderAnimal
import com.example.petfinder.domain.enumeration.TypeAnimal
import com.example.petfinder.extensions.launchWhenStarted
import com.example.petfinder.presentation.base.BaseFragment
import com.example.petfinder.presentation.screen.lisstpets.adapter.ListPetsAdapter
import com.example.petfinder.presentation.screen.model.PetModel
import com.example.petfinder.presentation.screen.profilepet.ProfilePetFragment
import kotlinx.coroutines.flow.onEach

class ListPetsFragment : BaseFragment<ListPetsViewModel>() {

    private var _binding: FragmentListPetsBinding? = null
    private val binding get() = _binding!!
    private val adapter: ListPetsAdapter = ListPetsAdapter(
        onOpenClick = { petModel -> navigateProfilePet(petModel) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPetsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvListPets.adapter = adapter
            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
            }
            adapter.addLoadStateListener { state ->
                flProgress.isVisible = state.refresh == LoadState.Loading
            }
        }
        with(viewModel) {
            pets.onEach { petModels ->
                adapter.submitData(petModels)
                binding.swipeRefresh.isRefreshing = false
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
        selectFilter()
        setTypePet()
        setGenderPet()
    }

    private fun setTypePet() {
        with(binding) {
            viewModel.petTypeFlow.onEach { typeAnimal ->
                when (typeAnimal) {
                    TypeAnimal.CAT ->
                        filterTypeCat.isChecked = true
                    TypeAnimal.DOG ->
                        filterTypeDog.isChecked = true
                    TypeAnimal.ANIMAL ->
                        filterTypeAllAnimal.isChecked = true
                    TypeAnimal.EMPTY -> {
                        filterTypeAllAnimal.isChecked = false
                        filterTypeCat.isChecked = false
                        filterTypeDog.isChecked = false
                    }
                }
                adapter.refresh()
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun setGenderPet() {
        with(binding) {
            viewModel.petGenderFlow.onEach { genderAnimal ->
                when (genderAnimal) {
                    GenderAnimal.MALE -> {
                        filterGenderMale.isChecked = true
                    }
                    GenderAnimal.FEMALE -> {
                        filterGenderFemale.isChecked = true
                    }
                    GenderAnimal.EMPTY -> {
                        filterGenderMale.isChecked = false
                        filterGenderFemale.isChecked = false
                    }
                }
                adapter.refresh()
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun selectFilter() {
        with(binding) {
            filterTypeDog.setOnClickListener {
                viewModel.changePetType(TypeAnimal.DOG)
                viewModel.changePetGender(GenderAnimal.EMPTY)
            }
            filterTypeAllAnimal.setOnClickListener {
                viewModel.changePetType(TypeAnimal.ANIMAL)
                viewModel.changePetGender(GenderAnimal.EMPTY)
            }
            filterTypeCat.setOnClickListener {
                viewModel.changePetType(TypeAnimal.CAT)
                viewModel.changePetGender(GenderAnimal.EMPTY)
            }
            filterGenderMale.setOnClickListener {
                viewModel.changePetGender(GenderAnimal.MALE)
                viewModel.changePetType(TypeAnimal.EMPTY)
            }
            filterGenderFemale.setOnClickListener {
                viewModel.changePetGender(GenderAnimal.FEMALE)
                viewModel.changePetType(TypeAnimal.EMPTY)
            }
        }
    }

    private fun navigateProfilePet(petModel: PetModel) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                ProfilePetFragment.newInstance(petModel)
            )
            .addToBackStack(ProfilePetFragment.PROFILE_PET)
            .commit()
    }

    companion object {
        fun newInstance() = ListPetsFragment()
    }
}