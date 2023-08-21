package com.apolisb42.shoppingcart.presenter.productslist

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class ProductsListPresenter(private val volleyHandler: VolleyHandler, val cartDao: CartDao, val productsView: MVPShoppingCart.ProductView)
    : MVPShoppingCart.IProductListPresenter{
    override fun getProducts(subCatId: String) {
        volleyHandler.getProducts(subCatId,object:ResponseCallback{

            override fun success(response: Any?) {
                (response as? ProductListResponse)?.let {
                    productsView.setSuccess(it)
                }
            }

            override fun failure() {
                productsView.setError()
            }

        })
    }

    override fun getCartItems(): List<CartItem> {
        return cartDao.fetchProduct()
    }


}