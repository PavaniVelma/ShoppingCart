package com.apolisb42.shoppingcart.model.productdetailsmodel

import com.apolisb42.shoppingcart.model.productdetailsmodel.Product

data class ProductDescriptionResponse(
    val message: String,
    val product: Product,
    val status: Int
)