package com.apolisb42.shoppingcart.model.categories

data class CategoriesResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)