package com.apolisb42.shoppingcart.model.productdetailsmodel

import androidx.room.Entity

data class Review(
    val full_name: String,
    val rating: String,
    val review: String,
    val review_date: String,
    val review_id: String,
    val review_title: String,
    val user_id: String
)