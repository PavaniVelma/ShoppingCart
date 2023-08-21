package com.apolisb42.shoppingcart.presenter.authentication

import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.UserProfileDetails
import com.apolisb42.shoppingcart.model.util.UserResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class SplashPresenter(private val volleyHandler: VolleyHandler, val splashView: MVPShoppingCart.SplashView): MVPShoppingCart.ISplashPresenter {
    override fun validateUser(emailId: String, password: String) {
        volleyHandler.loginUser(emailId, password, object : ResponseCallback{
            override fun success(response: Any?) {
                (response as? UserResponse)?.let {
                    UserProfileDetails.user = it.user
                    splashView.setSuccess()
                }
            }
            override fun failure() {
                splashView.setError()
            }
        })
    }
}