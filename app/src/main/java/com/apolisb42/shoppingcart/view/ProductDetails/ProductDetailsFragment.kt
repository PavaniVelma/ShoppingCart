package com.apolisb42.shoppingcart.view.ProductDetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentProductDetailsBinding
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.productdetailsmodel.ProductDescriptionResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.productslist.ProductDetailsPresenter
import com.squareup.picasso.Picasso


class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailsBinding
    private lateinit var presenter: ProductDetailsPresenter
    private var productId:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString("productId")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProductDetailsPresenter(VolleyHandler(requireContext()),object:MVPShoppingCart.ProductDetailsView{
            override fun setError() {
                TODO("Not yet implemented")
            }

            @SuppressLint("SetTextI18n")
            override fun setSuccess(productDescriptionResponse: ProductDescriptionResponse) {
                //productDescriptionResponse
                with(binding){
                    productDName.text = productDescriptionResponse.product.product_name
                    productDDescription.text = productDescriptionResponse.product.description
                    ratingBar.rating = productDescriptionResponse.product.average_rating.toFloat()
                    productDPrice.text = "$ ${productDescriptionResponse.product.price}"
                    Picasso.get().load(productDescriptionResponse.product.product_image_url).into(imageDPhone)

                }




                val adapter = SpecificationAdapter(productDescriptionResponse.product.specifications)

                binding.rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
                binding.rvSpecifications.adapter = adapter
            }

        })
        productId?.let{
            presenter.getProductDetails(it)
        }
    }

}