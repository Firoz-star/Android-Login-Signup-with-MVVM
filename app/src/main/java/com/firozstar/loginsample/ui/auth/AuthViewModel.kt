package com.firozstar.loginsample.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firozstar.loginsample.data.network.Resource
import com.firozstar.loginsample.data.repository.AuthRepository
import com.firozstar.loginsample.data.responses.LoginResponse
import com.firozstar.loginsample.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository){

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse : LiveData<Resource<LoginResponse>>
        get() = _loginResponse

     fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
         _loginResponse.value = Resource.Loading
            _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token:String) {
        repository.saveAuthToken(token)
    }

}