package com.firozstar.loginsample.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.firozstar.loginsample.data.network.AuthApi
import com.firozstar.loginsample.data.network.Resource
import com.firozstar.loginsample.data.repository.AuthRepository
import com.firozstar.loginsample.databinding.FragmentLoginBinding
import com.firozstar.loginsample.ui.base.BaseFragment
import com.firozstar.loginsample.ui.enable
import com.firozstar.loginsample.ui.handleApiError
import com.firozstar.loginsample.ui.home.HomeActivity
import com.firozstar.loginsample.ui.startNewActivity
import com.firozstar.loginsample.ui.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success ->{
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.user.access_token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                    is Resource.Failure ->handleApiError(it){ login() }
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener{
            login()
        }
    }

    private fun login(){
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }

    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater,container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)


}