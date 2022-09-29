package com.example.petfinder.di.module

import com.example.petfinder.di.PetFinderRetrofit
import com.example.petfinder.domain.response.Api
import com.example.petfinder.domain.response.ListPetApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun httpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJISE5nZlllNTVuUTBzMlFOd1dhTGVPSjIwdHFOeVhpUmtkYXo1UEtib0ozN2hCazdFRyIsImp0aSI6IjIxMTk2ZmI4ZTA3OWJlODZiNjAyNWM3MDk3Y2RkNzAzNjcyNjk4ZjQ0YmYzZWJmMTNhZGM2YzUwZjc1YzQwYjc0MGI5N2EwZTAwYTZhZjVmIiwiaWF0IjoxNjY0Mzc5ODQ2LCJuYmYiOjE2NjQzNzk4NDYsImV4cCI6MTY2NDM4MzQ0Niwic3ViIjoiIiwic2NvcGVzIjpbXX0.F4j3SnKdqYWeylieTX_SGOwAx7-RriPxtfUsPMHYuooCwMRjdEns1YBTz-XBCIa3eyLAcUWudRw5jsCtta8Xu4rrmti5qpuvRAuOJJprqE0-RicFa7gcR6tp_ydMznIgB5lmzMOOybEI9koyKrLYWQqWEX_B1KK6RSy2Qauwh3wlQ2ym3d_sv-aZ9_YsJOPmYQwhgeTGkMmZx9UVcELC7yNN3x2ADygahWUqgJfrkA0eirIWKc9TgjhAWGKLJ8OKhM-2rtjIMyGb5REJkr7pioZDbEv9Wubgio4-1MZHjLAIHkxXRmpO0TgDfWcAKdJG-UJSW7dQPcn4Jt0am4dolg"
                    )
                    .build()
                return@addInterceptor chain.proceed(request)
            }
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