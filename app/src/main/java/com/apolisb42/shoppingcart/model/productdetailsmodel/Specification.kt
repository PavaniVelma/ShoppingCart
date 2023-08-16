package com.apolisb42.shoppingcart.model.productdetailsmodel

import androidx.room.Entity

data class Specification(
    val display_order: String,
    val specification: String,
    val specification_id: String,
    val title: String
)