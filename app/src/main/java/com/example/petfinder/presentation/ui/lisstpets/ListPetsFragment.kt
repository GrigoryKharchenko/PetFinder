package com.example.petfinder.presentation.ui.lisstpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentListPetsBinding
import com.example.petfinder.presentation.base.BaseFragment
import com.example.petfinder.presentation.ui.lisstpets.adapter.ListPetsAdapter
import com.example.petfinder.presentation.ui.model.PetModel
import com.example.petfinder.presentation.ui.profilepet.ProfilePetFragment

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
            //TODO fix subscribe
            lifecycleScope.launchWhenStarted {
                viewModel.petFlow.collect(adapter::submitList)
            }
            tilSearchPet.setEndIconOnClickListener {
                viewModel.findPets(etSearchPet.text.toString())
            }
            swipeRefresh.setOnRefreshListener {
                viewModel.getListPets()
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