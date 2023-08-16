package com.apolisb42.shoppingcart.view.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentCategoryBinding
import com.apolisb42.shoppingcart.model.categories.CategoriesResponse
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.authentication.CategoryPresenter
import com.apolisb42.shoppingcart.presenter.authentication.SubCategoryPresenter
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.apolisb42.shoppingcart.view.subcategories.SubCategoryFragment
import com.google.android.material.snackbar.Snackbar


class CategoryFragment : Fragment(),ItemClickListener {
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var categoryPresenter:CategoryPresenter
    private lateinit var subCategoryFragment: SubCategoryFragment

    companion object{
        const val TAG = "SUPER CART"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.supportActionBar?.show()
        (activity as? ShoppingCartActivity)?.showNavDrawer()
         categoryPresenter = CategoryPresenter(VolleyHandler(requireContext()),object:MVPShoppingCart.CategoryView{
            override fun setError() {
                Snackbar.make(binding.root,"categories not found",Snackbar.LENGTH_LONG)
            }

            override fun setSuccess(categoriesResponse: CategoriesResponse) {
                val adapter = CategoryAdapter(categoriesResponse.categories, this@CategoryFragment)
                binding.rvCategories.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rvCategories.adapter = adapter
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

}