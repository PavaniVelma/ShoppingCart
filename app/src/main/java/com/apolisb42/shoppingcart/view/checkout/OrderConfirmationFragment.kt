package com.apolisb42.shoppingcart.view.checkout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.databinding.FragmentOrderConfirmationBinding
import com.apolisb42.shoppingcart.model.checkout.OrderDetailsResponse
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.checkout.OrderDetailsPresenter
import com.apolisb42.shoppingcart.view.ShoppingCartActivity

class OrderConfirmationFragment : Fragment() {

    private lateinit var binding:FragmentOrderConfirmationBinding
    private lateinit var presenter:OrderDetailsPresenter
    private var oderId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOrderConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.showBackButton()

        oderId = arguments?.getString("orderId")

        presenter = OrderDetailsPresenter(VolleyHandler.getInstance(requireContext()),object:MVPShoppingCart.OrderDetailsView{
            @SuppressLint("SetTextI18n")
            override fun setSuccess(orderDetailsResponse: OrderDetailsResponse) {

                val adapter = OrderItemsAdapter(orderDetailsResponse.order.items)
                binding.rvCartItemSummary.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCartItemSummary.adapter = adapter
                binding.tvOrderConfirmation.text = orderDetailsResponse.order.order_status

                binding.tvTotalPriceSummary.text ="$ ${ orderDetailsResponse.order.bill_amount}"
                binding.tvAddressTitleSummary.text = orderDetailsResponse.order.address_title
                binding.tvAddressBodySummary.text = orderDetailsResponse.order.address
                binding.tvPaymentOptionMode.text = orderDetailsResponse.order.payment_method

            }

            override fun setError() {
            }

        })

        oderId?.let { presenter.getOrderDetails(it) }
        binding.tvOrderIdNumber.text = "#${oderId}"

    }

    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("ORDER CONFIRMED")
    }





}