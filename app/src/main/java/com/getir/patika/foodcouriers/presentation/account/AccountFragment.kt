package com.getir.patika.foodcouriers.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.getir.patika.foodcouriers.presentation.account.adapter.PagerAdapter
import com.getir.patika.foodcouriers.R
import com.getir.patika.foodcouriers.databinding.FragmentAccountBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.wada811.viewbindingktx.viewBinding


class AccountFragment : Fragment(R.layout.fragment_account) {

    private val binding by viewBinding(FragmentAccountBinding::bind)
    private lateinit var pagerAdapter: PagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       pagerAdapter = PagerAdapter(parentFragmentManager,lifecycle).apply {
           addFragment(CreateAccountFragment())
           addFragment(LoginAccountFragment())
       }
        binding.viewPagerAccount.adapter = pagerAdapter

       TabLayoutMediator(binding.tabAccount,binding.viewPagerAccount){ tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Create Account"
                }
                1 -> {
                    tab.text = "Login Account"
                }
            }

       }.attach()

    }


    companion object {

    }
}