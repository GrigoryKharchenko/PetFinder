package com.example.petfinder.domain.interceptor

import android.content.SharedPreferences
import javax.inject.Inject

// TODO SharedPreferences to Datastore
class AuthorizationPreference @Inject constructor(private val sharedPreference: SharedPreferences) {

    var token: String?
        get() = sharedPreference.getString(TOKEN, "")
        set(value) = sharedPreference.edit().putString(TOKEN, value).apply()

    companion object {
        private const val TOKEN = "token"
    }
}