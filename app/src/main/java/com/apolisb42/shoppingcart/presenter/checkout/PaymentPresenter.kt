package com.apolisb42.shoppingcart.presenter.checkout

import com.apolisb42.shoppingcart.model.checkout.CheckOutDetails
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class PaymentPresenter():MVPShoppingCart.IPaymentPresenter{
    override fun savePaymentOption(paymentMode: String) {
        CheckOutDetails.paymentOption = paymentMode
    }
}


