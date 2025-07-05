package com.example.petrescueapp.data.network.token

import com.example.petrescueapp.data.network.models.AccessToken

interface AccessTokenProvider {
    suspend fun fetchAccessToken():AccessToken?

    fun refreshToken():AccessToken?

    fun saveToken(token:String?)

    fun token():String?

    fun lock(): Any

}