package com.example.petrescueapp.data.repository

import com.example.petrescueapp.data.network.mappers.PetApiMapper
import com.example.petrescueapp.data.network.models.ApiAnimals
import com.example.petrescueapp.data.network.retrofit.PetFinderApiService
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.domain.repository.PetRepository
import com.example.petrescueapp.utils.ResourceHolder

class PetRepositoryImplementation(
    private val apiService:PetFinderApiService,
    private val apiMapper: PetApiMapper<List<Pet>,ApiAnimals>
):PetRepository {
    override suspend fun getAnimal(page: Int): ResourceHolder<List<Pet>> {
        TODO("Not yet implemented")
    }
}