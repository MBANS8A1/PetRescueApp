package com.example.petrescueapp.token

import com.example.petrescueapp.data.local.StoragePref
import com.example.petrescueapp.data.network.models.AccessToken

class AccessTokenProviderImplementation(
    private val storagePref : StoragePref
):AccessTokenProvider {
    override suspend fun fetchAccessToken(): AccessToken? {
        TODO("Not yet implemented")
    }

    override fun refreshToken(): AccessToken? {
        return null
    }

    override fun saveToken(token: String?) {
       storagePref.saveToken(token)
    }

    override fun token(): String? {
       return storagePref.getToken()
    }

    override fun lock(): Any {
        return this
    }


}