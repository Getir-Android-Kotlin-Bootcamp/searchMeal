package com.getir.patika.foodcouriers.domain.usecase.user

import com.getir.patika.foodcouriers.common.domain.SingleParaMeterUseCase
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Login
import com.getir.patika.foodcouriers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SingleParaMeterUseCase<Login, Flow<BaseResponse<String>>> {

    override fun execute(param: Login): Flow<BaseResponse<String>>  = userRepository.setLogin(param)

}