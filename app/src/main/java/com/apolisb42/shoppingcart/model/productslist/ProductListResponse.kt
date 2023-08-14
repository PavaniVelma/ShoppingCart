package com.apolisb42.shoppingcart.model.productslist

data class ProductListResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)