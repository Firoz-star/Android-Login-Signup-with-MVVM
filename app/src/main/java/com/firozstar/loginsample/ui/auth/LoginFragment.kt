package com.firozstar.loginsample.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firozstar.loginsample.R
import com.firozstar.loginsample.databinding.FragmentLoginBinding
import com.firozstar.loginsample.network.AuthApi
import com.firozstar.loginsample.repository.AuthRepository
import com.firozstar.loginsample.ui.base.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater,container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}