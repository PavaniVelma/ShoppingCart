package com.apolisb42.shoppingcart.model.network

import android.content.Context

class VolleyConstants() {

    companion object{
        const val REGISTER_URL ="http://10.0.2.2/myshop/index.php/User/register"
        const val LOGIN_URL ="http://10.0.2.2/myshop/index.php/User/auth"
        const val CATEGORY_URL ="http://10.0.2.2/myshop/index.php/Category"
        const val IMAGE_URL = "http://10.0.2.2/myshop/images/"
        const val SUBCATEGORY__URL = "http://10.0.2.2/myshop/index.php/SubCategory?"
        const val PRODUCT_URL = "http://10.0.2.2/myshop/index.php/SubCategory/products/"
        const val PRODUCT_DETAIL_URL = "http://10.0.2.2/myshop/index.php/Product/details/"

    }
}