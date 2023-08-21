package com.apolisb42.shoppingcart.presenter.checkout

import com.apolisb42.shoppingcart.model.checkout.OrderDetailsResponse
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class OrderDetailsPresenter(private val volleyHandler: VolleyHandler, val orderDetailsView: MVPShoppingCart.OrderDetailsView)
    :MVPShoppingCart.IOrderDetailsPresenter{
    override fun getOrderDetails(orderId: String) {
        volleyHandler.getOrderDetails(orderId, object:ResponseCallback{
            override fun success(response: Any?) {
                (response as? OrderDetailsResponse)?.let{
                    orderDetailsView.setSuccess(it)
                }
            }
            override fun failure() {
                orderDetailsView.setError()
            }
        })
    }
}