package com.example.petfinder.di

import android.content.Context
import com.example.petfinder.PetFinderApp
import com.example.petfinder.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        RetrofitModule::class,
        DataStoreModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        PagingModule::class,
    ]
)
interface AppComponent : AndroidInjector<PetFinderApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }
}