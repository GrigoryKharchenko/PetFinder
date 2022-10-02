package com.example.petfinder.domain.interceptor

import okhttp3.Interceptor
import okhttp3.Response

interface ServiceInterceptor {

    fun addInterceptorToken(chain: Interceptor.Chain): Response
}

