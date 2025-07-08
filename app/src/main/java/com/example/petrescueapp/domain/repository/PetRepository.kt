package com.example.petrescueapp.domain.repository

import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.utils.ResourceHolder

interface PetRepository {
    suspend fun getAnimal(page:Int):ResourceHolder<List<Pet>>
}