package com.getir.patika.foodcouriers.domain.model

data class Order(
    val id: Int,
    val orderStatus: String,
    val orderTime: String,
    val paymentDetails: Any,
    val totalPrice: String,
    val userId: String
)