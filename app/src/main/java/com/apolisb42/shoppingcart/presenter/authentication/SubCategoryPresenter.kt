package com.apolisb42.shoppingcart.presenter.authentication

import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class SubCategoryPresenter(private val volleyHandler: VolleyHandler, val subCategoryView:MVPShoppingCart.SubCategoryView)
    :MVPShoppingCart.ISubCategoryPresenter{
    override fun getSubCategories(catId:String) {
        volleyHandler.getSubCategories(catId, object:ResponseCallback{

            override fun success(response: Any?) {
                (response as? SubcategoryResponse)?.let {
                    subCategoryView.setSuccess(it)
                }
            }
            override fun failure() {
                subCategoryView.setError()
            }

        })
    }
}