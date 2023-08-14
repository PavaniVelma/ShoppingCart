package com.apolisb42.shoppingcart.model.subcategories

data class SubcategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)