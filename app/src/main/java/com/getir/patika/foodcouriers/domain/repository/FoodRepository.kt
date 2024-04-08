package com.getir.patika.foodcouriers.domain.repository

import com.getir.patika.foodcouriers.data.remote.ApiService
import com.getir.patika.foodcouriers.data.remote.CallBack
import com.getir.patika.foodcouriers.di.IoDispatcher
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Food
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
){

    fun getAllFoods(): Flow<BaseResponse<List<Food>>> = callbackFlow {
        apiService.getAllFoods().enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

    fun getSearchFoods(search:String): Flow<BaseResponse<List<Food>>> = callbackFlow {
        apiService.getSearchFoods(search).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

    fun getCategoriesFoods(category: String): Flow<BaseResponse<List<Food>>> = callbackFlow {
        apiService.getCategoriesFoods(category).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

}