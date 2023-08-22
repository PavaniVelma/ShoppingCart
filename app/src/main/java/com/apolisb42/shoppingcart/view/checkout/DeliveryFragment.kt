package com.apolisb42.shoppingcart.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolisb42.shoppingcart.databinding.FragmentDeliveryBinding
import com.apolisb42.shoppingcart.model.checkout.Addresse
import com.apolisb42.shoppingcart.model.checkout.PostAddressRequest
import com.apolisb42.shoppingcart.model.checkout.UserAddressResponse
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.UserProfileDetails
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.checkout.DeliveryAddressPresenter


class DeliveryFragment : Fragment(), AddAddressClickListener, IsSelectedListener {

    private lateinit var binding:FragmentDeliveryBinding
    private lateinit var presenter: DeliveryAddressPresenter
    private lateinit var adapter : DeliveryAddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DeliveryAddressPresenter(VolleyHandler.getInstance(requireContext()),object:
            MVPShoppingCart.DeliveryAddressView{
            override fun setError() {
            }
            override fun setGetAddressSuccess(userAddressResponse: UserAddressResponse) {
                adapter = DeliveryAddressAdapter(userAddressResponse.addresses, this@DeliveryFragment)
                context?.let {
                    binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvAddress.adapter = adapter
                }
            }
            override fun setAddAdressSuccess() {
                presenter.getAddressDetails()
            }

        })
        binding.btnAddAddress.setOnClickListener {

            val bottomSheet = AddAddressFragment(this)
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheet.show(it1,"Add Address")
            }
        }
        presenter.getAddressDetails()
        binding.btnNext.setOnClickListener {
            (parentFragment as CheckoutFragment).moveToNext(2)
        }
    }
    override fun onAddClicked(title: String, address: String) {
        presenter.addNewAddress(PostAddressRequest(address, title, UserProfileDetails.user?.user_id?.toInt()?:0))
    }

    override fun saveAddress(address: Addresse) {
        presenter.saveSelectedAddress(address)
    }
}