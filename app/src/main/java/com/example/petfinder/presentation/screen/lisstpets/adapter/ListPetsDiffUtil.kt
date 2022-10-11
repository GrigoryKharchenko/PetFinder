package com.example.petfinder.presentation.screen.lisstpets.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.petfinder.presentation.screen.model.PetModel

class ListPetsDiffUtil : DiffUtil.ItemCallback<PetModel>() {
    override fun areItemsTheSame(oldItem: PetModel, newItem: PetModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PetModel, newItem: PetModel): Boolean =
        oldItem == newItem
}