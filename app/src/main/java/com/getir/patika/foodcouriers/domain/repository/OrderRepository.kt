package com.getir.patika.foodcouriers.domain.repository

import com.getir.patika.foodcouriers.data.remote.ApiService
import com.getir.patika.foodcouriers.data.remote.CallBack
import com.getir.patika.foodcouriers.di.IoDispatcher
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Orders
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun setOrders(userId: String, foodId: Int): Flow<BaseResponse<Any>>  = callbackFlow {
        apiService.setOrders(userId,foodId).enqueue(CallBack(this.channel))
        awaitClose{ close()}
    }.flowOn(ioDispatcher)

    fun getActiveOrders(userId: String): Flow<BaseResponse<Orders>> = callbackFlow {
        apiService.getActiveOrders(userId).enqueue(CallBack(this.channel))
        awaitClose{ close()}
    }.flowOn(ioDispatcher)

    fun setUpdateOrder(itemId: Int,quantity: Int): Flow<BaseResponse<Any>> = callbackFlow {
        apiService.setUpdateOrder(itemId,quantity).enqueue(CallBack(this.channel))
        awaitClose{ close()}
    }.flowOn(ioDispatcher)

    fun deleteCurrentOrders(userId: String): Flow<BaseResponse<Any>> = callbackFlow {
        apiService.deleteCurrentOrders(userId).enqueue(CallBack(this.channel))
        awaitClose{ close()}
    }.flowOn(ioDispatcher)

    fun deleteOrderWithById(userId: String,orderId: Int): Flow<BaseResponse<Any>> = callbackFlow {
        apiService.deleteOrderWithById(userId,orderId).enqueue(CallBack(this.channel))
        awaitClose{ close()}
    }.flowOn(ioDispatcher)

    fun getOrderComplete(userId: String): Flow<BaseResponse<Any>> = callbackFlow {
        apiService.getOrderComplete(userId).enqueue(CallBack(this.channel))
        awaitClose{ close()}
    }.flowOn(ioDispatcher)



}