package com.getir.patika.foodcouriers.domain.model

data class Orders(
    val items: List<Item>,
    val order: Order
)