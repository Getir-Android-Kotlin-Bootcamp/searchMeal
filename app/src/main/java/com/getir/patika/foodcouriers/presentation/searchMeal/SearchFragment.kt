package com.getir.patika.foodcouriers.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.getir.patika.foodcouriers.R
import com.getir.patika.foodcouriers.common.domain.ViewState
import com.getir.patika.foodcouriers.databinding.FragmentLoginAccountBinding
import com.getir.patika.foodcouriers.databinding.FragmentSearchBinding
import com.getir.patika.foodcouriers.databinding.ItemSearchBinding
import com.getir.patika.foodcouriers.databinding.ItemSearchMealBinding
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Food
import com.getir.patika.foodcouriers.presentation.account.AccountViewModel
import com.getir.patika.foodcouriers.presentation.adapter.SingleRecylerAdapter
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchMealViewModel by viewModels()
    private var mTextWatcher: TextWatcher? = null

    private val searchAdapter = SingleRecylerAdapter<ItemSearchMealBinding, Food>(
        { inflater,_,_ ->
            ItemSearchMealBinding.inflate(
                inflater,
                binding.rvSearchMeal,
                false)
        },
        { binding,item ->
            binding.searchMealName.text=item.name
            context?.let {
                Glide.with(it)
                    .load(item.imageUrl)
                    .into(binding.imgSearchMeal)
            }
            binding.searchMealDescription.text=item.description
            binding.mealPrice.text="$" + item.price
        }
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }


    private fun initListener() = textChanger()




    private  fun textChanger() {
        mTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length>=2){
                    with(viewModel){
                        viewModel.searchFood(s.toString())
                        viewLifecycleOwner.lifecycleScope.launch {
                            uiStateFood.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                                .collect { viewState ->
                                    when(viewState) {
                                        is ViewState.Success -> {
                                            val response = viewState.result as BaseResponse.Success
                                            searchAdapter.data=response.data
                                            binding. rvSearchMeal.layoutManager = LinearLayoutManager(context)
                                            binding. rvSearchMeal.adapter = searchAdapter
                                        }
                                        is ViewState.Error -> {
                                            val responseError = viewState.error
                                            Log.v("MyViewState",responseError)
                                        }
                                        is ViewState.Loading -> {
                                            Log.v("MyViewState","ViewState.Loading")
                                        }

                                    }

                                }
                        }
                    }

                }

            }

            override fun afterTextChanged(s: Editable) {}
        }
        binding.searchBar.addTextChangedListener(mTextWatcher)
    }




    companion object {

    }
}