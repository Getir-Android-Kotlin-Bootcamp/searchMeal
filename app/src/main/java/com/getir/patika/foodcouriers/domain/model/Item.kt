package com.getir.patika.foodcouriers.domain.model

data class Item(
    val foodId: Int,
    val id: Int,
    val orderId: Int,
    val price: String,
    val quantity: Int
)