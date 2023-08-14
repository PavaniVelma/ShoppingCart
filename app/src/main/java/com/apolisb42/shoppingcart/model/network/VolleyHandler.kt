package com.apolisb42.shoppingcart.model.network

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolisb42.shoppingcart.model.util.UserProfile
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.PRODUCT_DETAIL_URL
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.PRODUCT_URL
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.SUBCATEGORY__URL
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
                responseCallback.success(null)
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

        val queryParams = "$subCatId"
        val url = "$PRODUCT_URL$queryParams"
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


}