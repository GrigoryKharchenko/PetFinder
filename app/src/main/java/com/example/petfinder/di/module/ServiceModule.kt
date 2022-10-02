package com.example.petfinder.di.module

import com.example.petfinder.domain.interceptor.ServiceInterceptor
import com.example.petfinder.domain.interceptor.ServiceInterceptorImpl
import com.example.petfinder.domain.interceptor.TokenAuthenticatorImpl
import dagger.Binds
import dagger.Module
import okhttp3.Authenticator

@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindServiceInterceptor(serviceInterceptorImpl: ServiceInterceptorImpl): ServiceInterceptor

    @Binds
    abstract fun bindServiceToken(service: TokenAuthenticatorImpl): Authenticator

}