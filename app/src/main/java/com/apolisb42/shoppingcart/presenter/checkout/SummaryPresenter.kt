package com.apolisb42.shoppingcart.presenter.checkout

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.checkout.Addresse
import com.apolisb42.shoppingcart.model.checkout.CheckOutDetails
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.UserProfileDetails
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class SummaryPresenter (private val volleyHandler: VolleyHandler, private val cartDao: CartDao,val  summaryView: MVPShoppingCart.SummaryView): MVPShoppingCart.ISummaryPresenter{
    override fun getCartItems(): List<CartItem> {
        return cartDao.fetchProduct()
    }
    override fun getSelectedAddress(): Addresse? {
        return CheckOutDetails.address
    }

    override fun getSelectedPayment(): String? {
        return CheckOutDetails.paymentOption
    }
    override fun placeOrder() {
        UserProfileDetails.user?.let {
            volleyHandler.PostPlaceOrder(it.user_id,cartDao, object :ResponseCallback{
                override fun success(response: Any?) {
                    (response as? String)?.let {
                        summaryView.setSuccess(it)
                    }
                }

                override fun failure() {
                    summaryView.setError()
                }

            })
        }
    }

    override fun deleteCart() {
        cartDao.deleteCart()
    }
}