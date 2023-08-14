package com.apolisb42.shoppingcart.presenter.authentication

import com.apolisb42.shoppingcart.model.util.UserProfile
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class RegisterPresenter(private val volleyHandler: VolleyHandler, val registerView:MVPShoppingCart.RegisterView):
    MVPShoppingCart.IRegisterPresenter {
    override fun registerUser(userProfile: UserProfile) {
        volleyHandler.registerUser(userProfile = userProfile, object:ResponseCallback{
            override fun success(response: Any?) {
                registerView.setSuccess()
            }

            override fun failure() {
                registerView.setError()
            }

        })
    }


}