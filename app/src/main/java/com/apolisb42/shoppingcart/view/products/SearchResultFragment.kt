package com.apolisb42.shoppingcart.view.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentSearchResultBinding
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.searchproduct.SearchProductResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.authentication.SearchPresenter
import com.apolisb42.shoppingcart.view.ProductDetails.ProductDetailsFragment
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.apolisb42.shoppingcart.view.categories.ItemClickListener


class SearchResultFragment : Fragment(),ItemClickListener{
    private lateinit var binding:FragmentSearchResultBinding
    private lateinit var presenter: SearchPresenter
    private lateinit var productDetailsFragment: ProductDetailsFragment
    private var query:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultBinding.inflate(layoutInflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.showBackButton()
        query = arguments?.getString("query")

        presenter = SearchPresenter(VolleyHandler.getInstance(requireContext()),object:MVPShoppingCart.SearchProductView{
            override fun setSuccess(searchProductResponse: SearchProductResponse) {

                val adapter = ProductsAdapter(searchProductResponse.products,this@SearchResultFragment)
                binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
                binding.rvProducts.adapter = adapter
            }

            override fun setError() {
            }

        })
        query?.let{
            presenter.getSearchResult(it)
        }

    }
    override fun isSelected(id: String) {
        productDetailsFragment = ProductDetailsFragment()
        val bundle = Bundle()
        bundle.putString("productId",id)
        productDetailsFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,productDetailsFragment)?.addToBackStack(null)?.commit()
    }

}