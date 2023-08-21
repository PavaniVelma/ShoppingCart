package com.apolisb42.shoppingcart.model.network

class VolleyConstants() {
    companion object{
        //http://10.0.2.2:8080/
        const val BASE_URL = "http://192.168.1.155:8080/"
        const val REGISTER_URL ="${BASE_URL}myshop/index.php/User/register"
        const val LOGIN_URL ="${BASE_URL}myshop/index.php/User/auth"
        const val CATEGORY_URL ="${BASE_URL}myshop/index.php/Category"
        const val IMAGE_URL = "${BASE_URL}myshop/images/"
        const val SUBCATEGORY__URL = "${BASE_URL}myshop/index.php/SubCategory?"
        const val PRODUCT_URL = "${BASE_URL}myshop/index.php/SubCategory/products/"
        const val PRODUCT_DETAIL_URL = "${BASE_URL}myshop/index.php/Product/details/"
        const val ADDRESS_URL = "${BASE_URL}myshop/index.php/User/addresses/"
        const val ADD_ADDRESS_URL = "${BASE_URL}myshop/index.php/User/address"
        const val PLACE_ORDER_URL = "${BASE_URL}myshop/index.php/Order"
        const val ORDER_DETAILS_URL = "${BASE_URL}myshop/index.php/Order?"
        const val SEARCH_PRODUCT_URL = "${BASE_URL}myshop/index.php/Product/search?"
        const val LIST_ORDERS_URL = "${BASE_URL}myshop/index.php/Order/userOrders/"
    }
}