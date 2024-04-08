package com.getir.patika.foodcouriers.domain.usecase.order

import com.getir.patika.foodcouriers.common.domain.MultiParaMeterUseCase
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateOrdersUseCase @Inject constructor(
    private val orderRepository: OrderRepository
): MultiParaMeterUseCase<Any, Flow<BaseResponse<Any>>> {

    override fun execute(vararg params: Any): Flow<BaseResponse<Any>>  =
        orderRepository.setUpdateOrder(params[0] as Int, params[1] as Int)


}