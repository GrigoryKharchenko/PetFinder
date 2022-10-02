package com.example.petfinder.presentation.ui.lisstpets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.petfinder.R
import com.example.petfinder.databinding.ItemInfoPetBinding
import com.example.petfinder.presentation.ui.model.PetModel

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
            Glide.with(ivPhotoPet)
                .load(petModel.photo)
                .placeholder(R.drawable.photo)
                .transform(RoundedCorners(48))
                .into(ivPhotoPet)
        }
    }
}