package com.apolisb42.shoppingcart.model.network

interface ResponseCallback {
    fun success(response:Any?)
    fun failure()
}