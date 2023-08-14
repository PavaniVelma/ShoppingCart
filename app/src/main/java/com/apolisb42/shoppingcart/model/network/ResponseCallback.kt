package com.apolisb42.shoppingcart.model.network

import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse

interface ResponseCallback {

    fun success(response:Any?)
    fun failure()





}