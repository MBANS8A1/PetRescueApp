package com.example.petrescueapp

import android.content.Context
import com.example.petrescueapp.data.local.StoragePref
import com.example.petrescueapp.data.network.baseObject
import com.example.petrescueapp.data.network.interceptors.AccessInterceptor
import com.example.petrescueapp.data.network.interceptors.AccessTokenAuthorization
import com.example.petrescueapp.data.network.mappers.PetApiMapper
import com.example.petrescueapp.data.network.mappers.PetApiMapperImplementation
import com.example.petrescueapp.data.network.retrofit.PetFinderApiService
import com.example.petrescueapp.data.network.token.AccessTokenProvider
import com.example.petrescueapp.data.network.token.AccessTokenProviderImplementation
import com.example.petrescueapp.data.repository.PetRepositoryImplementation
import com.example.petrescueapp.domain.repository.PetRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }


object Graph {
    //For getting the token and saving the token
    lateinit var tokenStoragePref : StoragePref
    //For getting an authorised token and list of animals
    lateinit var apiService :  PetFinderApiService
    //For saving,refreshing,getting the token String and fetching the access token
    lateinit var accessTokenProvider: AccessTokenProvider
    //Get the animal(s) via entity-domain mapping
    lateinit var petRepository: PetRepository
    val apiMapper = PetApiMapperImplementation()
    private val okHttpClient:OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AccessInterceptor(accessTokenProvider))
            .authenticator(AccessTokenAuthorization(accessTokenProvider))
            .build()
    }


    fun provide(context:Context){
        tokenStoragePref = StoragePref(context)
        accessTokenProvider = AccessTokenProviderImplementation(tokenStoragePref)
        apiService = createPetFinderApiService()
        petRepository = PetRepositoryImplementation(apiService, apiMapper)
    }

    private fun createPetFinderApiService():PetFinderApiService{
        //telling retrofit data will come to me in the form of JSON
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseObject.BASE_URL)
            .addConverterFactory(json
                    .asConverterFactory(contentType)
            )
            .build()
            .create(PetFinderApiService::class.java)
    }
}
