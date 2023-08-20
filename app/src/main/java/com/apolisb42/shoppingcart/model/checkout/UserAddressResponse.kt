package com.apolisb42.shoppingcart.model.checkout

data class UserAddressResponse(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)