package com.apolisb42.shoppingcart.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.CustomTabBinding
import com.apolisb42.shoppingcart.databinding.FragmentCheckoutBinding
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.google.android.material.tabs.TabLayoutMediator


class CheckoutFragment : Fragment() {

    private lateinit var binding:FragmentCheckoutBinding
    private lateinit var checkoutViewPageAdapter: CheckoutViewPageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){

        val fragments = listOf(CartItemsFragment(), DeliveryFragment(), PaymentFragment(), SummaryFragment())

        checkoutViewPageAdapter  = CheckoutViewPageAdapter(fragments, this)

        with(binding){
            viewPager.adapter = checkoutViewPageAdapter

            TabLayoutMediator(tabLayout, viewPager){
                    tab, position ->
                val tabBinding = CustomTabBinding.inflate(layoutInflater,tabLayout,false)
                with(tabBinding){
                    when(position){
                        0 ->{
                            tvTextView.text = getString(R.string.cart_items)
                        }
                        1 ->{
                            tvTextView.text = getString(R.string.delivery)
                        }
                        2 ->{
                            tvTextView.text = getString(R.string.payment)
                        }
                        3 ->{
                            tvTextView.text = getString(R.string.summary)
                        }
                    }

                }
                tab.customView = tabBinding.root
            }.attach()
        }


    }

    fun moveToNext(position:Int){
        binding.viewPager.setCurrentItem(position,true)
    }
    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("Checkout")
    }
}