package com.example.petrescueapp.data.network.mappers

interface PetApiMapper<Domain,Entity> {

    fun mapToDomain(apiEntity:Entity):Domain
}