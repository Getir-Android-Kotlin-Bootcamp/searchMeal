package com.getir.patika.foodcouriers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.getir.patika.foodcouriers.databinding.FragmentOnboardingFavoritiesBinding
import com.wada811.viewbindingktx.viewBinding


class OnboardingFavoritiesFragment : Fragment(R.layout.fragment_onboarding_favorities) {

    private val binding by viewBinding(FragmentOnboardingFavoritiesBinding::bind)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() = with(binding) {
        btNext.setOnClickListener {
            if (isAdded) {
                findNavController().navigate(R.id.action_onboardingFavoritiesFragment_to_onboardingCheapFragment)
            }
        }
        btSkip.setOnClickListener {
            if(isAdded) {
                findNavController().navigate(R.id.action_onboardingFavoritiesFragment_to_searchFragment2)

                // findNavController().navigate(R.id.action_onboardingFavoritiesFragment_to_accountFragment)
            }
        }
        btNextArrow.setOnClickListener {
            if(isAdded) {
                findNavController().navigate(R.id.action_onboardingFavoritiesFragment_to_onboardingCheapFragment)
            }
        }
    }


    companion object {

    }
}