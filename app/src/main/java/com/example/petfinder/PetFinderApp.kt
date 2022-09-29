package com.example.petfinder

import com.example.petfinder.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PetFinderApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .context(this)
            .build()
}