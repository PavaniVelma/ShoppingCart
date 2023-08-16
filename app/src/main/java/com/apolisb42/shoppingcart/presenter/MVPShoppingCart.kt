package com.apolisb42.shoppingcart.presenter

import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.util.UserProfile
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.model.subcategories.SubcategoryResponse

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


}