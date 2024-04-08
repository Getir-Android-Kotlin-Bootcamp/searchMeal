package com.getir.patika.foodcouriers.domain.usecase.user

import com.getir.patika.foodcouriers.common.domain.SingleParaMeterUseCase
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.model.Profile
import com.getir.patika.foodcouriers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SingleParaMeterUseCase<String, Flow<BaseResponse<Profile>>> {

    override fun execute(param: String): Flow<BaseResponse<Profile>>  = userRepository.getProfile(param)


}