package com.example.petfinder.data.service

import com.example.petfinder.domain.preference.PreferenceManager
import com.example.petfinder.domain.repository.NetworkRepository
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticatorImpl @Inject constructor(
    private val networkRepository: Lazy<NetworkRepository>,
    private val preferenceManager: PreferenceManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val autoRepo = networkRepository.get() ?: return null
        return runBlocking {
            val tokenResponse = autoRepo.getNetworkToken().token
            preferenceManager.saveToken(tokenResponse)

            response.request.newBuilder().header("Authorization", "Bearer $tokenResponse").build()
        }
    }
}