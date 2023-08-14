package com.apolisb42.shoppingcart.model.util

data class UserResponse(
    val message: String,
    val status: Int,
    val user: User
)