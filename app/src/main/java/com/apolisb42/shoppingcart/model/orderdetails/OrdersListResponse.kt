package com.apolisb42.shoppingcart.model.orderdetails

data class OrdersListResponse(
    val message: String,
    val orders: List<Order>,
    val status: Int
)