package com.example.petrescueapp.data.network

import com.example.petrescueapp.BuildConfig

object baseObject {
    val BASE_URL = "https://api.petfinder.com/v2/"
    const val BASE_ENDPOINT = "animals"
    const val AUTH_ENDPOINT = "oauth2/token"
    const val API_KEY =BuildConfig.API_KEY_DEV
    const val SECRET_KEY =BuildConfig.SECRET_KEY_DEV
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"
    const val USER_TOKEN = "user_token"
}