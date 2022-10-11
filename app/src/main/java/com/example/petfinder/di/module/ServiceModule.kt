package com.example.petfinder.di.module

import com.example.petfinder.domain.service.PetFinderInterceptor
import com.example.petfinder.data.service.PetFinderInterceptorImpl
import com.example.petfinder.data.service.TokenAuthenticatorImpl
import dagger.Binds
import dagger.Module
import okhttp3.Authenticator

@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindServiceInterceptor(serviceInterceptorImpl: PetFinderInterceptorImpl): PetFinderInterceptor

    @Binds
    abstract fun bindServiceToken(service: TokenAuthenticatorImpl): Authenticator

}