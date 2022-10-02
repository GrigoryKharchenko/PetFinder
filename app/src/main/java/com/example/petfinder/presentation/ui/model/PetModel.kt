package com.example.petfinder.presentation.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PetModel(
    val id: Int,
    val type: String,
    val breeds: String,
    val age: String,
    val name: String,
    val photo: String,
    val gender: String,
    val size: String,
    val tags: String?
) : Parcelable