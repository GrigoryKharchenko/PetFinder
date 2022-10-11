package com.example.petfinder.presentation.screen.lisstpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentListPetsBinding
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
            etSearchPet.doOnTextChanged { text, _, _, _ ->
            }
        }
        with(viewModel) {
            pets.onEach { petModels ->
                adapter.submitData(petModels)
                binding.swipeRefresh.isRefreshing = false
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.addLoadStateListener { state ->
            binding.flProgress.isVisible = state.refresh == LoadState.Loading
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