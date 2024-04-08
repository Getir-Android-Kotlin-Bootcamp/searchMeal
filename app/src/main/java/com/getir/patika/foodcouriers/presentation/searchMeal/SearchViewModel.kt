package com.getir.patika.foodcouriers.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getir.patika.foodcouriers.common.domain.ViewState
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Food
import com.getir.patika.foodcouriers.domain.model.Login
import com.getir.patika.foodcouriers.domain.usecase.food.SearchFoodsUseCase
import com.getir.patika.foodcouriers.domain.usecase.user.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchMealViewModel @Inject constructor(
    private val  searchFoodUseCase: SearchFoodsUseCase
): ViewModel() {
    private val _uiStateFood: MutableStateFlow<ViewState<BaseResponse.Success<List<Food>>>> =
        MutableStateFlow(ViewState.Loading)
    val uiStateFood = _uiStateFood.asStateFlow()



    fun searchFood(food: String) {
        searchFoodUseCase.execute(food).map {
            when(val responseData: BaseResponse<List<Food>> = it) {
                is BaseResponse.Success -> {
                    ViewState.Success(responseData)
                }
                is BaseResponse.Error -> {
                    ViewState.Error(responseData.message)
                }
            }
        }.onEach { data ->
            _uiStateFood.emit(data)
        }.catch {
            _uiStateFood.emit(ViewState.Error(it.message.toString()))
        }.launchIn(viewModelScope)
    }


}