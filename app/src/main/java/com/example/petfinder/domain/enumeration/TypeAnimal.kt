package com.example.petfinder.domain.enumeration

enum class TypeAnimal(val type: String?) {
    ANIMAL(type = null),
    CAT(type = "cat"),
    DOG(type = "dog"),
    EMPTY(type = null)
}