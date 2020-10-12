package com.firozstar.loginsample.repository

import com.firozstar.loginsample.network.AuthApi

class AuthRepository(
    private val api:AuthApi
) : BaseRepository(){
    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }
}