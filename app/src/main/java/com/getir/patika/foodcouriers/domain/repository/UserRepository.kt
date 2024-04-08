package com.getir.patika.foodcouriers.domain.repository

import com.getir.patika.foodcouriers.data.remote.ApiService
import com.getir.patika.foodcouriers.data.remote.CallBack
import com.getir.patika.foodcouriers.di.IoDispatcher
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Location
import com.getir.patika.foodcouriers.domain.model.Login
import com.getir.patika.foodcouriers.domain.model.Profile
import com.getir.patika.foodcouriers.domain.model.Register
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun setRegister(register: Register): Flow<BaseResponse<String>> = callbackFlow {
        apiService.setRegister(register).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

    fun setLogin(login: Login): Flow<BaseResponse<String>> = callbackFlow {
        apiService.setLogin(login).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

    fun getProfile(userId: String): Flow<BaseResponse<Profile>> = callbackFlow {
        apiService.getProfile(userId).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

    fun setLocation(userId: String,location: Location): Flow<BaseResponse<Boolean>> = callbackFlow {
        apiService.setLocation(userId,location).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)


}