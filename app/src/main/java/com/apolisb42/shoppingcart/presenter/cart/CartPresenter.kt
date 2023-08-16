package com.apolisb42.shoppingcart.presenter.cart

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class CartPresenter(private val cartDao: CartDao):MVPShoppingCart.ICartPresenter {


    override fun getCartItems() = cartDao.fetchProduct()
}