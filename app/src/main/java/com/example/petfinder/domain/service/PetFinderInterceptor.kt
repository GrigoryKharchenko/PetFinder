package com.example.petfinder.domain.service

import okhttp3.Interceptor
import okhttp3.Response

interface PetFinderInterceptor {

    fun addInterceptorToken(chain: Interceptor.Chain): Response
}

