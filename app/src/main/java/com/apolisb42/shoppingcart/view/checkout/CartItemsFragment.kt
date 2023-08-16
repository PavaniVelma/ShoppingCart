package com.apolisb42.shoppingcart.view.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentCartItemsBinding
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.presenter.checkout.CartItemPresenter
import com.apolisb42.shoppingcart.view.cart.CartAdapter


class CartItemsFragment : Fragment() {

    private lateinit var binding:FragmentCartItemsBinding
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var presenter: CartItemPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartItemsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
    }

    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)!!
        cartDao = appDatabase.getCartDao()

        presenter = (CartItemPresenter(cartDao))

        val adapter = CartItemsAdapter(presenter.getCartItems())
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter
    }

}