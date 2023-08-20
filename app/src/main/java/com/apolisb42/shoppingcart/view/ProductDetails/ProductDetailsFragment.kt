package com.apolisb42.shoppingcart.view.ProductDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.databinding.FragmentProductDetailsBinding
import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.productdetailsmodel.Product
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.productslist.ProductDetailsPresenter
import com.apolisb42.shoppingcart.view.QuantityStepperListener
import com.apolisb42.shoppingcart.view.ShoppingCartActivity


class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailsBinding
    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var cartDao: CartDao
    private lateinit var viewAdapter: ImageViewAdapter
    private var productId:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString("productId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.showBackButton()
        initDB()
        presenter = ProductDetailsPresenter(VolleyHandler.getInstance(requireContext()), cartDao,object:MVPShoppingCart.ProductDetailsView{
            override fun setError() {
            }

            @SuppressLint("SetTextI18n")
            override fun setSuccess(productDescriptionResponse: ProductDescriptionResponse) {
                //productDescriptionResponse
                with(binding){
                    productDName.text = productDescriptionResponse.product.product_name
                    productDDescription.text = productDescriptionResponse.product.description
                    ratingBar.rating = productDescriptionResponse.product.average_rating.toFloat()
                    productDPrice.text = "$ ${productDescriptionResponse.product.price}"
//                    val image = "${VolleyConstants.IMAGE_URL}${productDescriptionResponse.product.images.first().image}"

                }

                binding.addToCart.setOnClickListener {
                    with(productDescriptionResponse.product){
                        addToCart(this, 1)
                    }
                    it.isVisible = false
                    binding.quantityStepper.isVisible = true
                }

                presenter.getProductWithId(productDescriptionResponse.product.product_id)?.let {
                    binding.quantityStepper.setQuantity(it.quantity)
                    binding.addToCart.isVisible = false
                    binding.quantityStepper.isVisible = true
                }

                binding.quantityStepper.setQuantityStepperListener(object : QuantityStepperListener{
                    override fun onQuantityChanged(quantity: Int) {
                        with(productDescriptionResponse.product){
                            addToCart(this, quantity)
                        }

                    }

                    override fun onQuantityZero() {
                        with(productDescriptionResponse.product){
                            val cartItem = CartItem(
                                id=product_id,
                                unitPrice = price,
                                product_image_url= product_image_url,
                                product_name = product_name,
                                description = description

                            )
                            presenter.deleteItemInCart(cartItem)
                        }
                        binding.addToCart.isVisible = true
                        binding.quantityStepper.isVisible = false
                    }

                })

                val adapter = SpecificationAdapter(productDescriptionResponse.product.specifications)
                binding.rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
                binding.rvSpecifications.adapter = adapter

                val reviewAdapter = ReviewsAdapter(productDescriptionResponse.product.reviews)
                binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
                binding.rvReviews.adapter = reviewAdapter

                viewAdapter = ImageViewAdapter(productDescriptionResponse.product.images, requireContext())
                binding.viewPagerImage.adapter = viewAdapter

            }



        })

        productId?.let{
            presenter.getProductDetails(it)
        }
    }

    private fun initDB() {
        val database = AppDatabase.getAppDatabase(requireContext())
        cartDao = database.getCartDao()
    }

    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("Details")
    }

    fun addToCart(product: Product, quantity:Int){
        with(product) {
            val cartItem = CartItem(
                id = product_id,
                unitPrice = price,
                product_image_url = product_image_url,
                product_name = product_name,
                description = description,
                quantity = quantity

            )
            presenter.addToCart(cartItem)
        }
    }

}