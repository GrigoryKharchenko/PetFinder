package com.example.petfinder.data.service

import com.example.petfinder.domain.preference.PreferenceManager
import com.example.petfinder.domain.service.PetFinderInterceptor
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class PetFinderInterceptorImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
) : PetFinderInterceptor {
    override fun addInterceptorToken(chain: Interceptor.Chain): Response {
        val token = runBlocking { preferenceManager.getToken() }
        val request = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer $token"
        )
            .build()
        return chain.proceed(request)
    }


}