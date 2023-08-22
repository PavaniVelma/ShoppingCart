package com.apolisb42.shoppingcart.view.products

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentProductListBinding
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao
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
    private lateinit var adapter: ProductsAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subCategoryId = arguments?.getString("sub_category_id")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(layoutInflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDb()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(listener, IntentFilter("Quantity_Update"))

        (activity as? ShoppingCartActivity)?.showBackButton()
        productsListPresenter = ProductsListPresenter(VolleyHandler.getInstance(requireContext()), cartDao,object:MVPShoppingCart.ProductView{
            override fun setError() {
            }
            override fun setSuccess(productListResponse: ProductListResponse) {
                adapter = ProductsAdapter(productListResponse.products,this@ProductListFragment)
                binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
                binding.rvProducts.adapter = adapter
                adapter.updateCartItems(productsListPresenter.getCartItems())
            }

        })

        subCategoryId?.let{
            productsListPresenter.getProducts(it)
        }
    }

    private fun initDb(){
        appDatabase = AppDatabase.getAppDatabase(requireContext())
        cartDao = appDatabase.getCartDao()
    }

    override fun isSelected(id: String) {
        productDetailsFragment = ProductDetailsFragment()
        val bundle = Bundle()
        bundle.putString("productId",id)
        productDetailsFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,productDetailsFragment)?.addToBackStack(null)?.commit()
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(listener)
    }

    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("SMART PHONES")
    }


    val listener = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(this@ProductListFragment::adapter.isInitialized
                && this@ProductListFragment::productsListPresenter.isInitialized){
                adapter.updateCartItems(productsListPresenter.getCartItems())
            }
        }

    }

}