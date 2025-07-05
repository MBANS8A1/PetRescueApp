package com.example.petrescueapp

import android.content.Context
import com.example.petrescueapp.data.local.StoragePref
import com.example.petrescueapp.data.network.baseObject
import com.example.petrescueapp.data.network.retrofit.PetFinderApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }


object Graph {
    //For getting the token and saving the token
    lateinit var tokenStoragePref : StoragePref
    //For getting an authorised token and list of animals
    lateinit var apiService :  PetFinderApiService

    fun provide(context:Context){
        tokenStoragePref = StoragePref(context)
        apiService = createPetFinderApiService()
    }

    private fun createPetFinderApiService():PetFinderApiService{
        //telling retrofit data will come to me in the form of JSON
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(baseObject.BASE_URL)
            .addConverterFactory(json
                    .asConverterFactory(contentType)
            )
            .build()
            .create(PetFinderApiService::class.java)
    }
}
