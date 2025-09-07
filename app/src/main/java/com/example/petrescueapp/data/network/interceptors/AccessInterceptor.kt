package com.example.petrescueapp.data.network.interceptors

import com.example.petrescueapp.data.network.token.AccessTokenProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessInterceptor(
    private val accessTokenProvider : AccessTokenProvider
):Interceptor {

    companion object{
        const val TAG = "myPetRescueApp"
    }

    //Build the structure of the request header with property name and value
    private fun Request.signedRequest() : Request{
       return newBuilder()
           .addHeader("Authorization","Bearer ${accessTokenProvider.token()}")
           .build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

}