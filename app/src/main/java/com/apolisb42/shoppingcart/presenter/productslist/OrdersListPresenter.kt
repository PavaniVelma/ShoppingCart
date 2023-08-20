package com.apolisb42.shoppingcart.presenter.productslist

import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.orderdetails.OrdersListResponse
import com.apolisb42.shoppingcart.model.util.UserProfileDetails
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class OrdersListPresenter(private val volleyHandler: VolleyHandler, val ordersListView: MVPShoppingCart.OrdersListView):
MVPShoppingCart.IOrdersListPresenter{
    override fun getListOrders() {
        UserProfileDetails.user?.user_id?.let {
            volleyHandler.listOfOrders(it, object:ResponseCallback{
                override fun success(response: Any?) {

                    (response as? OrdersListResponse)?.let{

                        ordersListView.setSuccess(it)
                    }
                }

                override fun failure() {
                    ordersListView.setError()
                }

            })
        }
    }
}