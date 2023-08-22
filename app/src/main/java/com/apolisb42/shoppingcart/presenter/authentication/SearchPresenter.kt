package com.apolisb42.shoppingcart.presenter.authentication

import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.searchproduct.SearchProductResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class SearchPresenter(private val volleyHandler: VolleyHandler, val searchProductView: MVPShoppingCart.SearchProductView)
    :MVPShoppingCart.ISearchProductPresenter{
    override fun getSearchResult(query: String) {
        volleyHandler.searchProduct(query, object: ResponseCallback {
            override fun success(response: Any?) {
                (response as? SearchProductResponse)?.let{
                    searchProductView.setSuccess(response)
                }
            }
            override fun failure() {
                searchProductView.setError()
            }

        })
    }
}