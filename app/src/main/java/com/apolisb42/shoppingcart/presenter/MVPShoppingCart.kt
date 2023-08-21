package com.apolisb42.shoppingcart.presenter

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.checkout.Addresse
import com.apolisb42.shoppingcart.model.checkout.OrderDetailsResponse
import com.apolisb42.shoppingcart.model.checkout.PostAddressRequest
import com.apolisb42.shoppingcart.model.checkout.UserAddressResponse
import com.apolisb42.shoppingcart.model.orderdetails.OrdersListResponse
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.model.searchproduct.SearchProductResponse
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse
import com.apolisb42.shoppingcart.model.util.UserProfile

interface MVPShoppingCart {
    interface IRegisterPresenter{
        fun registerUser(userProfile: UserProfile)
    }
    interface RegisterView{
       fun setError()
       fun setSuccess()
    }
    interface ILoginPresenter{
        fun validateUser(emailId:String, password:String)
    }

    interface ISplashPresenter{
        fun validateUser(emailId: String, password: String)
    }

    interface LoginView{

        fun setError()
        fun setSuccess()

    }

    interface ICategoryPresenter{
        fun getCategories()

    }
    interface CategoryView{
        fun setError()
        fun setSuccess(categoriesResponse: CategoriesResponse)

    }


    interface ISubCategoryPresenter{

        fun getSubCategories(catId:String)
    }
    interface SubCategoryView{
        fun setError()
        fun setSuccess(subcategoryResponse: SubcategoryResponse)
    }

    interface IProductListPresenter{

        fun getProducts(subCatId:String)

        fun getCartItems(): List<CartItem>
    }

    interface ProductView{
        fun setError()
        fun setSuccess(productListResponse: ProductListResponse)
    }

    interface IProductDetailsPresenter{

        fun getProductDetails(productId:String)

        fun addToCart(cartItem: CartItem)

        fun deleteItemInCart(cartItem: CartItem)

        fun getProductWithId(productId: String): CartItem?
    }

    interface ProductDetailsView{
        fun setError()
        fun setSuccess(productDescriptionResponse: ProductDescriptionResponse)
    }

    interface ICartPresenter{

        fun getCartItems(): List<CartItem>
    }

    interface IDeliveryAddressPresenter{

        fun getAddressDetails()

        fun saveSelectedAddress(address: Addresse)
        fun addNewAddress(postAddressRequest: PostAddressRequest)
    }

    interface DeliveryAddressView{
        // Error
        fun setError()

        //Success for get Address
        fun setGetAddressSuccess(userAddressResponse: UserAddressResponse)

        //Success for post Address
        fun setAddAdressSuccess()
    }

    interface ISummaryPresenter{

        fun getCartItems(): List<CartItem>
        fun getSelectedAddress():Addresse?
        fun getSelectedPayment():String?

        fun placeOrder()

        fun deleteCart()
    }

    interface SummaryView{
        fun setSuccess(orderId: String)
        fun setError()

    }

    interface IPaymentPresenter{
        fun savePaymentOption(paymentMode:String)
    }

    interface IOrderDetailsPresenter{
        fun getOrderDetails(orderId:String)
    }

    interface OrderDetailsView{

        fun setSuccess(orderDetailsResponse: OrderDetailsResponse)

        fun setError()
    }

    interface ISearchProductPresenter{

        fun getSearchResult(query: String)
    }

    interface SearchProductView{
        fun setSuccess(searchProductResponse: SearchProductResponse)

        fun setError()
    }

    interface SplashView{
        fun setSuccess()
        fun setError()
    }

    interface IOrdersListPresenter{

        fun getListOrders()
    }

    interface OrdersListView{

        fun setSuccess(ordersListResponse: OrdersListResponse)

        fun setError()
    }





}