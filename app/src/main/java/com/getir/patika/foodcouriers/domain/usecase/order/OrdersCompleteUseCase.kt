package com.getir.patika.foodcouriers.domain.usecase.order

import com.getir.patika.foodcouriers.common.domain.SingleParaMeterUseCase
import com.getir.patika.foodcouriers.domain.model.BaseResponse
import com.getir.patika.foodcouriers.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrdersCompleteUseCase @Inject constructor(
    private val orderRepository: OrderRepository
): SingleParaMeterUseCase<String, Flow<BaseResponse<Any>>> {

    override fun execute(param: String): Flow<BaseResponse<Any>> = orderRepository.getOrderComplete(param)


}