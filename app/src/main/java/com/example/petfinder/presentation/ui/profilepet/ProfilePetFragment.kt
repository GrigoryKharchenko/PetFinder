package com.example.petfinder.presentation.ui.profilepet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentProfilePetBinding
import com.example.petfinder.presentation.base.BaseFragment
import com.example.petfinder.presentation.ui.model.PetModel

class ProfilePetFragment : BaseFragment<ProfilePetViewModel>() {

    private var _binding: FragmentProfilePetBinding? = null
    private val binding get() = _binding!!

    private var petModel: PetModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolBar.setNavigationOnClickListener { goBack() }
        }
        getArgumentPet()
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    private fun setPet(pet: PetModel) {
        binding.run {
            tvTypePet.text = getString(R.string.type_pet, pet.type)
            tvBreedsPet.text = getString(R.string.breeds_pet, pet.breeds)
            tvAgePet.text = getString(R.string.age_pet, pet.age)
            tvNamePet.text = getString(R.string.name_pet, pet.name)
            tvGenderPet.text = getString(R.string.gender_pet, pet.gender)
            tvSizePet.text = getString(R.string.size_pet, pet.size)
            if (pet.tags?.isNotEmpty() == true) {
                tvTagsPet.text = getString(R.string.tags_pet, pet.tags)
            } else {
                tvTagsPet.text = getString(R.string.tags_pet, "Unknown")
            }
            //TODO Glide to ext
            Glide.with(ivPhotoPet)
                .load(petModel?.photo)
                .placeholder(R.drawable.photo)
                .transform(RoundedCorners(48))
                .into(ivPhotoPet)
        }
    }

    private fun getArgumentPet() {
        petModel = arguments?.getParcelable(ARG_PET)
        petModel?.let { setPet(it) }
    }


    companion object {
        fun newInstance(pet: PetModel?) = ProfilePetFragment().apply {
            arguments = bundleOf(ARG_PET to pet)
        }

        const val ARG_PET = "ArgPet"
        const val PROFILE_PET = "ProfilePet"
    }
}