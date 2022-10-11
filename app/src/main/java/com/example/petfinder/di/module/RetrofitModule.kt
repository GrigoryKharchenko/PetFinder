package com.example.petfinder.di.module

import com.example.petfinder.di.PetFinderRetrofit
import com.example.petfinder.domain.service.PetFinderInterceptor
import com.example.petfinder.data.network.Api
import com.example.petfinder.data.network.ListPetApi
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
        petFinderInterceptor: PetFinderInterceptor,
        authenticator: Authenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                petFinderInterceptor.addInterceptorToken(chain)
            }
            .authenticator(authenticator)
            .build()

    @Singleton
    @Provides
    @PetFinderRetrofit
    fun listPetFinderClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(Api.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePetFinderApi(@PetFinderRetrofit retrofit: Retrofit): ListPetApi =
        retrofit.create(ListPetApi::class.java)

}