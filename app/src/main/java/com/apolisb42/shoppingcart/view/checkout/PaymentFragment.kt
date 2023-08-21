package com.apolisb42.shoppingcart.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentPaymentBinding
import com.apolisb42.shoppingcart.presenter.checkout.PaymentPresenter

class PaymentFragment : Fragment() {

    private lateinit var binding:FragmentPaymentBinding
    private lateinit var presenter: PaymentPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = PaymentPresenter()

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                R.id.btn_internet_banking ->{
                    presenter.savePaymentOption( "Internet Banking")
                }
                R.id.btn_card -> {
                    presenter.savePaymentOption("Debit Card/Credit Card")
                }
                R.id.btn_payPal ->{
                    presenter.savePaymentOption("Pay pal")
                }
                R.id.btn_delivery ->{
                    presenter.savePaymentOption("Cash On Delivery")
                }
            }
        }
        binding.btnNext.setOnClickListener {
            (parentFragment as CheckoutFragment).moveToNext(3)
        }
    }
}