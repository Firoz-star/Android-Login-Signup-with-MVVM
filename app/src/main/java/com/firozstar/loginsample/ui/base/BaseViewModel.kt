package com.firozstar.loginsample.ui.base

import androidx.lifecycle.ViewModel
import com.firozstar.loginsample.data.network.UserApi
import com.firozstar.loginsample.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
):ViewModel(){
    suspend fun logout(api: UserApi) = repository.logout(api)
}