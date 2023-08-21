package com.apolisb42.shoppingcart.presenter.authentication

import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class CategoryPresenter(private val volleyHandler: VolleyHandler, val categoryView: MVPShoppingCart.CategoryView)
    :MVPShoppingCart.ICategoryPresenter {
    override fun getCategories() {
        volleyHandler.getCategories(object:ResponseCallback{

            override fun success(response: Any?) {
                (response as? CategoriesResponse)?.let {
                    categoryView.setSuccess(it)
                }
            }
            override fun failure() {
                categoryView.setError()
            }
        })
    }
}