package com.example.petrescueapp.token

import com.example.petrescueapp.Graph
import com.example.petrescueapp.data.local.StoragePref
import com.example.petrescueapp.data.network.models.AccessToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessTokenProviderImplementation(
    private val storagePref : StoragePref
):AccessTokenProvider {
    override suspend fun fetchAccessToken(): AccessToken? = withContext(
        Dispatchers.IO){
        val accessToken = Graph.apiService.getAuthToken()
        saveToken(accessToken.accessToken)
        accessToken //returning the access token
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