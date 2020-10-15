package com.firozstar.loginsample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.firozstar.loginsample.data.network.Resource
import com.firozstar.loginsample.data.network.UserApi
import com.firozstar.loginsample.data.repository.UserRepository
import com.firozstar.loginsample.data.responses.User
import com.firozstar.loginsample.databinding.FragmentHomeBinding
import com.firozstar.loginsample.ui.base.BaseFragment
import com.firozstar.loginsample.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    binding.progressbar.visible(false)
                    upDateUI(it.value.user)
                }
                is Resource.Loading ->{
                    binding.progressbar.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener{
            logout()
        }
    }

    private fun upDateUI(user: User) {
        with(binding){
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): UserRepository {

        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }


}