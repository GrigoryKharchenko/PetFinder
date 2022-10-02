package com.example.petfinder.domain.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ServiceInterceptorImpl @Inject constructor(
    //TODO заменить на репо
    private val sharedPreference: AuthorizationPreference
) : ServiceInterceptor {
    override fun addInterceptorToken(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer ${getToken()}"
        )
            .build()
        return chain.proceed(request)
    }

    private fun getToken(): String = sharedPreference.token.orEmpty()

}