package com.apolisb42.shoppingcart.model.checkout

data class PostAddressRequest(
    val address: String,
    val title: String,
    val user_id: Int
)