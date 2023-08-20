package com.apolisb42.shoppingcart.view.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentCategoryBinding
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.authentication.CategoryPresenter
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.apolisb42.shoppingcart.view.products.SearchResultFragment
import com.apolisb42.shoppingcart.view.subcategories.SubCategoryFragment
import com.google.android.material.snackbar.Snackbar


class CategoryFragment : Fragment(),ItemClickListener {
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var categoryPresenter:CategoryPresenter
    private lateinit var subCategoryFragment: SubCategoryFragment

 


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentCategoryBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.supportActionBar?.show()
        (activity as? ShoppingCartActivity)?.showNavDrawer()
         categoryPresenter = CategoryPresenter(VolleyHandler.getInstance(requireContext()),object:MVPShoppingCart.CategoryView{
            override fun setError() {
                Snackbar.make(binding.root,"categories not found",Snackbar.LENGTH_LONG).show()
            }

            override fun setSuccess(categoriesResponse: CategoriesResponse) {
                val adapter = CategoryAdapter(categoriesResponse.categories, this@CategoryFragment)
                context?.let {
                    binding.rvCategories.layoutManager = GridLayoutManager(it,2)
                    binding.rvCategories.adapter = adapter
                }
            }
         })
        categoryPresenter.getCategories()

    }

    override fun isSelected(id: String) {
        val bundle =Bundle()
        bundle.putString("id",id)
        subCategoryFragment = SubCategoryFragment()
        subCategoryFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,subCategoryFragment)?.
        addToBackStack(null)?.commit()
    }
    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("SUPER CART")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu,menu)
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    query?.let{
                        // Create a new fragment called SearchResultsFragment.
                        // Send query as argument to SearchResultsFragment.
                        //  activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,searchResultsFragment)?.commit()
                        // In SearchResultsFragment use this query to make API call.
                        // Show results in Recyclerview.
                        val fragment = SearchResultFragment()
                        val bundle = Bundle()
                        bundle.putString("query",query)
                        fragment.arguments = bundle
                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,fragment)?.
                        addToBackStack(null)?.commit()
                    }

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return true
                }

            })
            queryHint = "Search by category"
        }
    }

}