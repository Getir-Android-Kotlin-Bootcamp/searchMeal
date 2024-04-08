package com.getir.patika.foodcouriers.domain.model

data class Food(
    val category: String,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val orderCount: Int,
    val price: String,
    val rating: Double
)