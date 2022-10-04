package com.example.petfinder.presentation.screen.lisstpets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.R
import com.example.petfinder.databinding.ItemInfoPetBinding
import com.example.petfinder.extensions.loadPhoto
import com.example.petfinder.presentation.screen.model.PetModel

class ListPetsViewHolder(private val binding: ItemInfoPetBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        petModel: PetModel,
        onOpenClick: (PetModel) -> Unit
    ) {
        with(binding) {
            tvTypePet.text = itemView.context.getString(R.string.type_pet, petModel.type)
            tvBreedsPet.text = itemView.context.getString(R.string.breeds_pet, petModel.breeds)
            tvAgePet.text = itemView.context.getString(R.string.age_pet, petModel.age)
            tvNamePet.text = itemView.context.getString(R.string.name_pet, petModel.name)
            root.setOnClickListener {
                onOpenClick(petModel)
            }
            ivPhotoPet.loadPhoto(petModel.photo)
        }
    }
}