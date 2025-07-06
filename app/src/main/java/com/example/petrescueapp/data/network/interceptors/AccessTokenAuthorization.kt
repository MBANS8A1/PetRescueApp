package com.example.petrescueapp.data.network.interceptors

import com.example.petrescueapp.data.network.token.AccessTokenProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthorization(
    private val tokenProvider : AccessTokenProvider
):Authenticator {
    private val Response.retryCount:Int
        get() {
            var currentResponse = priorResponse
        }

    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}