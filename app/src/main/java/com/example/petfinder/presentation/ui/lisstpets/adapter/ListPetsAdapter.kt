package com.example.petfinder.presentation.ui.lisstpets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.petfinder.databinding.ItemInfoPetBinding
import com.example.petfinder.presentation.ui.model.PetModel

class ListPetsAdapter(
    val onOpenClick: (PetModel) -> Unit
) : ListAdapter<PetModel, ListPetsViewHolder>(ListPetsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPetsViewHolder {
        val itemView = ItemInfoPetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListPetsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListPetsViewHolder, position: Int) {
        holder.bind(getItem(position), onOpenClick)
    }
}