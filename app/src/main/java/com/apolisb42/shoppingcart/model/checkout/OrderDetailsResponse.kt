package com.apolisb42.shoppingcart.model.checkout

data class OrderDetailsResponse(
    val message: String,
    val order: Order,
    val status: Int
)