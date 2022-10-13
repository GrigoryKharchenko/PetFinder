package com.example.petfinder.presentation.screen.lisstpets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.petfinder.databinding.ItemInfoPetBinding
import com.example.petfinder.presentation.screen.model.PetModel

class ListPetsAdapter(
    val onOpenClick: (PetModel) -> Unit
) : PagingDataAdapter<PetModel, ListPetsViewHolder>(ListPetsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPetsViewHolder {
        val itemView = ItemInfoPetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListPetsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListPetsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onOpenClick) }
    }
}