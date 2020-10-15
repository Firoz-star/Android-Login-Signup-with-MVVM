package com.firozstar.loginsample.data.repository

import com.firozstar.loginsample.data.UserPreferences
import com.firozstar.loginsample.data.network.AuthApi
import com.firozstar.loginsample.data.network.UserApi

class UserRepository(
    private val api:UserApi,

) : BaseRepository(){
    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}