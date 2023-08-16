package com.apolisb42.shoppingcart.presenter.productslist

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class ProductDetailsPresenter(private val volleyHandler: VolleyHandler, private val cartDao: CartDao, val detailsView: MVPShoppingCart.ProductDetailsView)
    :MVPShoppingCart.IProductDetailsPresenter{
    override fun getProductDetails(productId: String) {
        volleyHandler.getProductDetails(productId,object:ResponseCallback{
            override fun success(response: Any?) {
                (response as? ProductDescriptionResponse)?.let{
                    detailsView.setSuccess(it)
                }
            }

            override fun failure() {
                detailsView.setError()
            }

        })
    }

    override fun addToCart(cartItem: CartItem) {
        cartDao.insertProduct(cartItem)
    }

    override fun deleteItemInCart(cartItem: CartItem) {
        cartDao.deleteProduct(cartItem)
    }

    override fun getProductWithId(productId: String): CartItem? {
        return cartDao.fetchProductWithId(productId)
    }

}