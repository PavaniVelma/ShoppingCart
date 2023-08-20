package com.apolisb42.shoppingcart.view.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentCart2Binding
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.presenter.cart.CartPresenter
import com.apolisb42.shoppingcart.view.ProductDetails.ProductDetailsFragment
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.apolisb42.shoppingcart.view.categories.ItemClickListener
import com.apolisb42.shoppingcart.view.checkout.CheckoutFragment


class CartFragment : Fragment(),ItemClickListener {

    private lateinit var binding:FragmentCart2Binding
    private lateinit var productDetailsFragment:ProductDetailsFragment
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCart2Binding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
        (activity as? ShoppingCartActivity)?.showNavDrawer()
        val presenter = CartPresenter(cartDao)
        val cartItems = presenter.getCartItems()
        var cartTotal = 0F
        cartItems.forEach {
            cartTotal += it.price.toFloat()
        }
        binding.tvTotalPrice.text = "$ $cartTotal"
        val adapter = CartAdapter(cartItems,this)
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter

        binding.btnCheckout.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container,CheckoutFragment())?.addToBackStack(null)
                ?.commit()
        }
    }
    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)
        cartDao = appDatabase.getCartDao()
    }
    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("Cart")
    }
    override fun isSelected(id: String) {
        productDetailsFragment = ProductDetailsFragment()
        val bundle = Bundle()
        bundle.putString("productId",id)
        productDetailsFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,productDetailsFragment)?.addToBackStack(null)?.commit()
    }


}