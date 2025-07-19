package com.example.petrescueapp.data.network.mappers

import com.example.petrescueapp.data.network.models.Address
import com.example.petrescueapp.data.network.models.ApiAnimals
import com.example.petrescueapp.data.network.models.Contact
import com.example.petrescueapp.data.network.models.Photo
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.domain.models.PetOwnerContacts
import com.example.petrescueapp.domain.models.PetPhoto

class PetApiMapperImplementation:PetApiMapper<List<Pet>,ApiAnimals> {
    companion object{
        private const val EMPTY_DATA = "unknown"
    }

    override fun mapToDomain(apiEntity: ApiAnimals): List<Pet> {
        return apiEntity.animals.map { animal ->
            animal.run{
                Pet(
                    id = formatData(id.toString()),
                    age = formatData(age),
                    breeds = formatData(breeds?.primary),
                    colors=formatData(colors?.primary),
                    contact = formatContacts(contact),
                    description = formatData(description),
                    distance = formatData(distance?.toString()),
                    gender = formatData(gender),
                    url = formatData(url),
                    name = formatData(name),
                    photos = formatPhotos(photos),
                    size = formatData(size),
                    species = formatData(species),
                    status = formatData(status),
                    tags = tags ?: emptyList(),
                    type = formatData(type),
                    currentPage = apiEntity.pagination.currentPage ?: 0

                )
           }
        }
    }


    private fun formatContacts(contact:Contact?):PetOwnerContacts{
        return PetOwnerContacts(
            address=formatData(formatAddress(contact?.address)),
            email=formatData(contact?.email),
            phone = formatData(contact?.phone)

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

    private fun formatPhotos(photoList:List<Photo>?):List<PetPhoto>{
        return photoList?.map{photo ->
            PetPhoto(
                full =formatData(photo.full),
                large =formatData(photo.large),
                medium = formatData(photo.medium),
                small=formatData(photo.small)
            )
        } ?: listOf()
    }

    private fun formatData(data:String?):String{
        return data ?: EMPTY_DATA
    }
}

