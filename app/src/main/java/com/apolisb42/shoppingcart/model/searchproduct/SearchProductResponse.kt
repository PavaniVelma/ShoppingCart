package com.apolisb42.shoppingcart.model.searchproduct

import com.apolisb42.shoppingcart.model.productslist.Product

data class SearchProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)