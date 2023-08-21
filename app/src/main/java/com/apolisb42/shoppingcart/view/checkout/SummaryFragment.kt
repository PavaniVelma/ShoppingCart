package com.apolisb42.shoppingcart.view.checkout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentSummaryBinding
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.checkout.SummaryPresenter

class SummaryFragment : Fragment() {

    private lateinit var binding:FragmentSummaryBinding
    private lateinit var presenter:SummaryPresenter
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater,container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmPlaceOrder.setOnClickListener {
            presenter.placeOrder()
        }
        initDB()

        val cartItems = presenter.getCartItems()
        var totalPrice = 0F
        cartItems.forEach {
            totalPrice+=it.price.toFloat()
        }
        binding.tvTotalPriceSummary.text = "$ ${totalPrice}"
    }

    override fun onResume() {
        super.onResume()
        binding.tvAddressTitleSummary.text = presenter.getSelectedAddress()?.title
        binding.tvAddressBodySummary.text = presenter.getSelectedAddress()?.address
        binding.tvPaymentOptionMode.text = presenter.getSelectedPayment()


    }

    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)
        cartDao = appDatabase.getCartDao()

        presenter = SummaryPresenter(VolleyHandler.getInstance(requireContext()), cartDao, object : MVPShoppingCart.SummaryView{
            override fun setSuccess(orderId: String) {
                val fragment = OrderConfirmationFragment()
                val bundle = Bundle().apply {
                    putString("orderId", orderId)
                }
                fragment.arguments = bundle
                presenter.deleteCart()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, fragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }

            override fun setError() {
                // Show error
            }
        })

        val adapter = CartItemsAdapter(presenter.getCartItems())
        binding.rvCartItemSummary.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItemSummary.adapter = adapter
    }
}