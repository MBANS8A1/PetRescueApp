package com.example.petrescueapp.data.network.interceptors
import com.example.petrescueapp.data.network.token.AccessTokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthorization(
    private val tokenProvider : AccessTokenProvider
):Authenticator {
    private val Response.retryCount:Int
        get() {
            //initially currentResponse will be null
            var currentResponse = priorResponse
            var result = 0
            while(currentResponse != null){
                result++
                currentResponse = currentResponse.priorResponse
            }
            return result
        }

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(tokenProvider.lock()){
            return when{
                response.retryCount > 2 -> null
                else -> runBlocking {response.createSignedRequest()}
            }
        }
    }

    private suspend fun Response.createSignedRequest():Request? = try{
        tokenProvider.fetchAccessToken()
        request //return
    }
    catch(e:Exception){
        e.printStackTrace()
        null //return
    }
}