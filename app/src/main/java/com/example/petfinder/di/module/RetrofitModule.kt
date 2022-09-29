package com.example.petfinder.di.module

import com.example.petfinder.di.PetFinderRetrofit
import com.example.petfinder.domain.interceptor.ServiceInterceptor
import com.example.petfinder.domain.response.Api
import com.example.petfinder.domain.response.ListPetApi
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun httpClient(
        serviceInterceptor: ServiceInterceptor,
        authenticator: Authenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                serviceInterceptor.addInterceptorToken(chain)
            }
            .authenticator(authenticator)
            .build()

    @Singleton
    @Provides
    @PetFinderRetrofit
    fun listPetFinderClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePetFinderApi(@PetFinderRetrofit retrofit: Retrofit): ListPetApi =
        retrofit.create(ListPetApi::class.java)

}