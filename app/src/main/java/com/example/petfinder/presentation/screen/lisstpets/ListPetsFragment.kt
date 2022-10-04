package com.example.petfinder.presentation.screen.lisstpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentListPetsBinding
import com.example.petfinder.presentation.base.BaseFragment
import com.example.petfinder.presentation.screen.lisstpets.adapter.ListPetsAdapter
import com.example.petfinder.presentation.screen.model.PetModel
import com.example.petfinder.presentation.screen.profilepet.ProfilePetFragment
import kotlinx.coroutines.launch

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
                viewModel.setQuery(text?.toString() ?: "")
            }
            swipeRefresh.setOnRefreshListener {
                viewModel.getListPets()
                swipeRefresh.isRefreshing = false
            }
        }
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    petFlow.collect { listPetModel ->
                        adapter.submitList(listPetModel)
                        binding.flProgress.isVisible = false
                    }
                }
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