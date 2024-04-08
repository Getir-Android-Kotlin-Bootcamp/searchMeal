package com.getir.patika.foodcouriers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.getir.patika.foodcouriers.databinding.FragmentHomeBinding
import com.getir.patika.foodcouriers.databinding.ItemCategoryBinding
import com.getir.patika.foodcouriers.databinding.ItemCategoryViewBinding
import com.getir.patika.foodcouriers.databinding.ItemSearchBinding
import com.getir.patika.foodcouriers.presentation.adapter.SingleRecylerAdapter
import com.wada811.viewbindingktx.viewBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val searchAdapter = SingleRecylerAdapter<ItemSearchBinding,String>(
        { inflater,_,_ ->
            ItemSearchBinding.inflate(
                inflater,
                binding.rvHome,
                false)
        },
        { binding,item ->

        }
    )


    private val categoryItemAdapter = SingleRecylerAdapter<ItemCategoryViewBinding,String>(
        { inflater,_,_ ->
            ItemCategoryViewBinding.inflate(
                inflater,
                binding.rvHome,
                false)
        },
        { binding,item ->
            binding.chip.text = item

            binding.root.setOnClickListener {

            }
        }
    )

    private val categoryAdapter = SingleRecylerAdapter<ItemCategoryBinding,String>(
        { inflater,_,_ ->
            ItemCategoryBinding.inflate(
                inflater,
                binding.rvHome,
                false)
        },
        { binding,item ->
          binding.rvCategory.adapter = categoryItemAdapter
        }
    )


    private val  concatAdapter = ConcatAdapter(
        searchAdapter,
        categoryAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()

    }

    private fun initListener() = with(binding) {
        rvHome.adapter = concatAdapter
    }

    private fun initData() {
        searchAdapter.data = listOf("Searhview")
        categoryItemAdapter.data = listOf("Burger","Pizza")
    }

    companion object {

    }
}