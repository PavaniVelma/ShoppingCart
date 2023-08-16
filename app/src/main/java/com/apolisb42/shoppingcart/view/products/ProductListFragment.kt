package com.apolisb42.shoppingcart.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentProductListBinding
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.productslist.ProductListResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.productslist.ProductsListPresenter
import com.apolisb42.shoppingcart.view.ProductDetails.ProductDetailsFragment
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.apolisb42.shoppingcart.view.categories.ItemClickListener


class ProductListFragment : Fragment(), ItemClickListener {

    private lateinit var binding : FragmentProductListBinding
    private var subCategoryId: String? = null
    private lateinit var productsListPresenter: ProductsListPresenter
    private lateinit var productDetailsFragment: ProductDetailsFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subCategoryId = arguments?.getString("sub_category_id")


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? ShoppingCartActivity)?.showBackButton()
        productsListPresenter = ProductsListPresenter(VolleyHandler(requireContext()),object:MVPShoppingCart.ProductView{
            override fun setError() {

            }

            override fun setSuccess(productListResponse: ProductListResponse) {
                val adapter = ProductsAdapter(productListResponse.products,this@ProductListFragment)
                binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
                binding.rvProducts.adapter = adapter
            }

        })

        subCategoryId?.let{
            productsListPresenter.getProducts(it)
        }



    }

    override fun isSelected(id: String) {
        productDetailsFragment = ProductDetailsFragment()
        val bundle = Bundle()
        bundle.putString("productId",id)
        productDetailsFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,productDetailsFragment)?.addToBackStack(null)?.commit()
    }


    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("SMART PHONES")
    }



}