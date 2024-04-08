package com.getir.patika.foodcouriers.presentation.account

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.getir.patika.foodcouriers.R
import com.getir.patika.foodcouriers.common.domain.ViewState
import com.getir.patika.foodcouriers.databinding.FragmentLoginAccountBinding
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Login
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginAccountFragment : Fragment(R.layout.fragment_login_account) {

    private val viewModel: AccountViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginAccountBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initListener() = with(binding) {
        btSignup.setOnClickListener {
            viewModel.setLogin(Login(etEmail.text.toString(),etPassword.text.toString()))
            progress.visibility = View.VISIBLE
        }

    }

    private fun initObserver() = with(viewModel) {
       viewLifecycleOwner.lifecycleScope.launch {
           uiStateLogin.flowWithLifecycle(viewLifecycleOwner.lifecycle)
               .collect { viewState ->
                   when(viewState) {
                    is ViewState.Success -> {
                        val response = viewState.result as BaseResponse.Success
                        Log.v("MyViewState",response.data)
                        binding.progress.visibility = View.GONE
                    }
                    is ViewState.Error -> {
                        val responseError = viewState.error
                        Log.v("MyViewState",responseError)
                        binding.progress.visibility = View.GONE
                    }
                    is ViewState.Loading -> {
                        Log.v("MyViewState","ViewState.Loading")
                    }

                   }

               }
       }
    }


    companion object {

    }
}