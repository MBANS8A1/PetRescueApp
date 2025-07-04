package com.example.petrescueapp.data.network.retrofit

import com.example.petrescueapp.data.network.models.AccessToken
import com.example.petrescueapp.data.network.models.ApiAnimals
import com.example.petrescueapp.data.network.models.baseObject.API_KEY
import com.example.petrescueapp.data.network.models.baseObject.AUTH_ENDPOINT
import com.example.petrescueapp.data.network.models.baseObject.BASE_ENDPOINT
import com.example.petrescueapp.data.network.models.baseObject.CLIENT_ID
import com.example.petrescueapp.data.network.models.baseObject.CLIENT_SECRET
import com.example.petrescueapp.data.network.models.baseObject.SECRET_KEY
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//suspend function(s) for making asynchronous API requests
interface PetFinderApiService {
    @GET(BASE_ENDPOINT)
    suspend fun getAnimals(
       @Query("page") page : Int
    ) :ApiAnimals


    @POST(AUTH_ENDPOINT)
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field(CLIENT_ID) clientId: String = API_KEY,
        @Field(CLIENT_SECRET) clientSecret: String = SECRET_KEY,
        @Field("grant_type") grantType: String = "client_Credentials"
    ):AccessToken
}