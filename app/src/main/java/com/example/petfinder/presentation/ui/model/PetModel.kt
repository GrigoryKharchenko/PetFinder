package com.example.petfinder.presentation.ui.model


data class PetModel(
    val id:Int,
    val type: String,
    val breeds: String,
    val age: String,
    val name: String,
    val photo:String
)