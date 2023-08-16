package com.apolisb42.shoppingcart.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentCart2Binding
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao


class CartFragment : Fragment() {

    private lateinit var binding:FragmentCart2Binding
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCart2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
        val adapter = CartAdapter(cartDao.fetchProduct())
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter
    }
    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)!!
        cartDao = appDatabase.getCartDao()
    }


}