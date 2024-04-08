package com.getir.patika.foodcouriers.domain.usecase.user

import com.getir.patika.foodcouriers.common.domain.MultiParaMeterUseCase
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Location
import com.getir.patika.foodcouriers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocationUseCase @Inject constructor(
    private val userRepository: UserRepository
) : MultiParaMeterUseCase<Any, Flow<BaseResponse<Boolean>>> {

    override fun execute(vararg params: Any): Flow<BaseResponse<Boolean>>  =
        userRepository.setLocation(params[0] as String,params[1] as Location)

}