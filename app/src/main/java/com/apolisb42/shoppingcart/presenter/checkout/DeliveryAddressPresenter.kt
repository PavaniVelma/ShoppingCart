package com.apolisb42.shoppingcart.presenter.checkout

import com.apolisb42.shoppingcart.model.checkout.Addresse
import com.apolisb42.shoppingcart.model.checkout.CheckOutDetails
import com.apolisb42.shoppingcart.model.checkout.PostAddressRequest
import com.apolisb42.shoppingcart.model.checkout.UserAddressResponse
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.UserProfileDetails
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class DeliveryAddressPresenter(private val volleyHandler: VolleyHandler, val deliveryAddressView: MVPShoppingCart.DeliveryAddressView)
    :MVPShoppingCart.IDeliveryAddressPresenter {
    override fun getAddressDetails() {

        UserProfileDetails.user?.user_id?.let {
            volleyHandler.getAddressDetails(it, object :ResponseCallback{
                override fun success(response: Any?) {
                    (response as? UserAddressResponse)?.let {
                        deliveryAddressView.setGetAddressSuccess(it)
                    }
                }
                override fun failure() {
                    deliveryAddressView.setError()
                }

            })
        }
    }
    override fun saveSelectedAddress(address: Addresse) {
        CheckOutDetails.address = address
    }
    override fun addNewAddress(postAddressRequest: PostAddressRequest) {
        volleyHandler.PostAddressDetails(postAddressRequest, object: ResponseCallback{
            override fun success(response: Any?) {
                deliveryAddressView.setAddAdressSuccess()
            }
            override fun failure() {
                deliveryAddressView.setError()
            }
        })
    }
}