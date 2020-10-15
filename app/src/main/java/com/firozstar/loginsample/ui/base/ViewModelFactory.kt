package com.firozstar.loginsample.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firozstar.loginsample.data.repository.AuthRepository
import com.firozstar.loginsample.data.repository.BaseRepository
import com.firozstar.loginsample.data.repository.UserRepository
import com.firozstar.loginsample.ui.auth.AuthViewModel
import com.firozstar.loginsample.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)-> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java)-> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}