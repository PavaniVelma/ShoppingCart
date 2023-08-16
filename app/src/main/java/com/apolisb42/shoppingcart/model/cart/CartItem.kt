package com.apolisb42.shoppingcart.model.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey
    val id: String,
    val unitPrice: String,
    var quantity: Int = 0,
    val price:String = (unitPrice.toFloat()*quantity).toString(),
    val product_name: String,
    val description: String,
    val product_image_url: String,
)
