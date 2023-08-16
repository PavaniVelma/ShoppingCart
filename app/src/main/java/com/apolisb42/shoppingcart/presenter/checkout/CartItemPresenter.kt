package com.apolisb42.shoppingcart.presenter.checkout

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class CartItemPresenter(private val cartDao: CartDao): MVPShoppingCart.ICartPresenter{
    override fun getCartItems(): List<CartItem> {
        return cartDao.fetchProduct()
    }
}