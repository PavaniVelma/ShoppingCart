package com.apolisb42.shoppingcart.model.network

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.checkout.CheckOutDetails
import com.apolisb42.shoppingcart.model.checkout.OrderDetailsResponse
import com.apolisb42.shoppingcart.model.checkout.PostAddressRequest
import com.apolisb42.shoppingcart.model.checkout.UserAddressResponse
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.ADDRESS_URL
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.PLACE_ORDER_URL
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.PRODUCT_DETAIL_URL
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.PRODUCT_URL
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.SUBCATEGORY__URL
import com.apolisb42.shoppingcart.model.orderdetails.OrdersListResponse
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.model.searchproduct.SearchProductResponse
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse
import com.apolisb42.shoppingcart.model.util.UserProfile
import com.apolisb42.shoppingcart.model.util.UserResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class VolleyHandler(val context: Context) {

    companion object{
        @SuppressLint("StaticFieldLeak")
        /*
        @Volatile: meaning that writes to this field are immediately made visible to other threads.
         */
        @Volatile
        private var instance:VolleyHandler?= null
        fun getInstance(context:Context) = instance?:synchronized(this){
            instance?:VolleyHandler(context)
        }
    }

    private val requestQueue:RequestQueue by lazy{
        Volley.newRequestQueue(context.applicationContext)
    }

    fun registerUser(userProfile: UserProfile, responseCallback: ResponseCallback){

        val jsonRequest = JSONObject()
        jsonRequest.apply {
            put("full_name", userProfile.userName)
            put("mobile_no", userProfile.phnNum)
            put("email_id", userProfile.emailId)
            put("password", userProfile.password)
        }

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            VolleyConstants.REGISTER_URL,
            jsonRequest,
            {
                if(it.getString("message").isNotBlank()||it.getString("message").isNotEmpty()){
                    responseCallback.success(null)
                }else{
                    responseCallback.failure()
                }
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)


    }

    fun loginUser(emailId:String, password:String,responseCallback: ResponseCallback){

        val jsonRequest = JSONObject()
        jsonRequest.put("email_id", emailId)
        jsonRequest.put("password", password)

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            VolleyConstants.LOGIN_URL,
            jsonRequest,
        {

            if(it.getString("message") == "Login successful"){
                val typeToken = object:TypeToken<UserResponse>(){}
                val response = Gson().fromJson(it.toString(),typeToken)
                responseCallback.success(response)
            }else{
                responseCallback.failure()
            }


        },{
                Log.i("logiError",it.message.toString())
        }
        )
        requestQueue.add(stringRequest)

    }

    fun getCategories(responseCallback: ResponseCallback){

        val stringRequest = StringRequest(
            Request.Method.GET,
            VolleyConstants.CATEGORY_URL,
            {
                val typeToken = object:TypeToken<CategoriesResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getSubCategories(catId:String,responseCallback: ResponseCallback){

        val queryParams = "category_id=$catId"
        val url = "$SUBCATEGORY__URL$queryParams"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<SubcategoryResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                if(response.message == "Success") {
                    responseCallback.success(response)
                } else{
                    responseCallback.failure()
                }
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getProducts(subCatId:String,responseCallback: ResponseCallback){

        val url = "$PRODUCT_URL$subCatId"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<ProductListResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getProductDetails(productId:String,responseCallback: ResponseCallback){

        val url = "$PRODUCT_DETAIL_URL$productId"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<ProductDescriptionResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getAddressDetails(userId:String,responseCallback: ResponseCallback){

        val url = "$ADDRESS_URL$userId"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<UserAddressResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun PostAddressDetails(postAddressResponse: PostAddressRequest, responseCallback: ResponseCallback){


        val params = HashMap<String,String>()
        params.put("user_id",postAddressResponse.user_id.toString())
        params.put("title",postAddressResponse.title)
        params.put("address",postAddressResponse.address)

        val request = JSONObject(Gson().toJson(postAddressResponse))

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            VolleyConstants.ADD_ADDRESS_URL,
            request,
            {
                responseCallback.success(null)
            },
            {
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun PostPlaceOrder(userid:String, cartDao: CartDao ,responseCallback: ResponseCallback){

        val deliveryObject = JSONObject().apply {
            put("title", CheckOutDetails.address?.title)
            put("address",CheckOutDetails.address?.address)
        }

        var totalAmount = 0F
        val cartItemsArray = JSONArray().apply {
            cartDao.fetchProduct().forEachIndexed { index, cartItem ->
                val cartItemObject = JSONObject().apply {
                    put("product_id", cartItem.id.toInt())
                    put("quantity", cartItem.quantity.toLong())
                    put("unit_price", cartItem.unitPrice.toFloat())
                }
                totalAmount += cartItem.price.toFloat()
                put(index, cartItemObject)

            }
        }

        val jsonRequest = JSONObject()
        jsonRequest.apply {
            put("user_id",userid.toInt())
            put("items",cartItemsArray)
            put("delivery_address", deliveryObject)
            put("bill_amount", totalAmount)
            put("payment_method", "COD")
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            PLACE_ORDER_URL,
            jsonRequest,
            {
            responseCallback.success(it.getString("order_id"))
            },
            {
                responseCallback.failure()
            }
        )
        requestQueue.add(request)


    }

    fun getOrderDetails(orderId:String, responseCallback: ResponseCallback){

        val queryParams = "order_id=${orderId}"
        val url =" ${VolleyConstants.ORDER_DETAILS_URL}${queryParams}"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<OrderDetailsResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun searchProduct(search:String, responseCallback: ResponseCallback){

        val queryParams = "query=${search}"
        val url =" ${VolleyConstants.SEARCH_PRODUCT_URL}${queryParams}"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<SearchProductResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)


    }

    fun listOfOrders(userId:String,responseCallback: ResponseCallback){

        val stringRequest = StringRequest(
            Request.Method.GET,
            VolleyConstants.LIST_ORDERS_URL+userId,
            {
                val typeToken = object:TypeToken<OrdersListResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }






}