package com.example.petrescueapp.data.network.mappers

import com.example.petrescueapp.data.network.models.Address
import com.example.petrescueapp.data.network.models.ApiAnimals
import com.example.petrescueapp.data.network.models.Contact
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.domain.models.PetOwnerContacts

class PetApiMapperImplementation:PetApiMapper<List<Pet>,ApiAnimals> {
    companion object{
        private const val EMPTY_DATA = "unknown"
    }

    override fun mapToDomain(apiEntity: ApiAnimals): List<Pet> {
        return apiEntity.animals.map { animal ->
            animal.run{
                Pet(

                )
           }
        }
    }


    private fun formatContacts(contact:Contact?):PetOwnerContacts{
        return PetOwnerContacts(
            address=formatData(formatAddress(contact?.address))
        )
    }

    private fun formatAddress(address: Address?):String{
        //a period/full stop to help separate the address components
        val dot = "u25CF"
        if(address != null){
            return "${address.city}$dot${address.country}"
        }
        return ""
    }

    private fun formatData(data:String?):String{
        return data ?: EMPTY_DATA
    }
}

