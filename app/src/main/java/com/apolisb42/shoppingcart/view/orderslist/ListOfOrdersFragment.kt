package com.apolisb42.shoppingcart.view.orderslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.databinding.FragmentListOfOrdersBinding
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.orderdetails.OrdersListResponse
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.productslist.OrdersListPresenter


class ListOfOrdersFragment : Fragment() {

    private lateinit var binding:FragmentListOfOrdersBinding
    private lateinit var presenter: OrdersListPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListOfOrdersBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OrdersListPresenter(VolleyHandler.getInstance(requireContext()),object:MVPShoppingCart.OrdersListView{
            override fun setSuccess(ordersListResponse: OrdersListResponse) {

                val adapter = OrdersAdapter(ordersListResponse.orders)
                binding.rvListOfOrders.layoutManager = LinearLayoutManager(requireContext())
                binding.rvListOfOrders.adapter = adapter
            }

            override fun setError() {
            }

        })
        presenter.getListOrders()

    }
}