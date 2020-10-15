package com.firozstar.loginsample.data.repository

import com.firozstar.loginsample.data.UserPreferences
import com.firozstar.loginsample.data.network.AuthApi

class AuthRepository(
    private val api:AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){
    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}