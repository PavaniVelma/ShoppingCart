package com.apolisb42.shoppingcart.presenter.authentication

import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.network.ResponseCallback
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse
import com.apolisb42.shoppingcart.model.util.PreferencesHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart

class LoginPresenter(private val volleyHandler: VolleyHandler, val loginView: MVPShoppingCart.LoginView): MVPShoppingCart.ILoginPresenter {
    override fun validateUser(emailId: String, password: String) {
         volleyHandler.loginUser(emailId, password, object:ResponseCallback{
             override fun success(response: Any?) {
                 loginView.setSuccess()
             }
             override fun failure() {
                 loginView.setError()
             }

         })
    }

    /*
    val image = catergory.category_image_url -> smartphones.png
    val url = "http://localhost/myshop/images/$image" ->
    picasso.get().load(url).into(view)
    * */
}