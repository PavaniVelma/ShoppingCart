package com.apolisb42.shoppingcart.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolisb42.shoppingcart.databinding.FragmentRegisterBinding
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.UserProfile
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.authentication.RegisterPresenter
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var presenter:RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setDisplayShowHomeEnabled(false)
        initRegister()
    }

    private fun initRegister(){
        presenter = RegisterPresenter(VolleyHandler.getInstance(requireContext()),object:MVPShoppingCart.RegisterView{
            override fun setError() {
                Snackbar.make(binding.root,"Failed to Register com.apolisb42.shoppingcart.model.util.User",Snackbar.LENGTH_LONG).show()
            }

            override fun setSuccess() {
                activity?.supportFragmentManager?.popBackStack()
            }

        })
        binding.buttonRegister.setOnClickListener {
            with(binding){
                val user = UserProfile(
                    userName = etFullName.text.toString(),
                    phnNum = etMobileNum.text.toString(),
                    emailId = etEmil.text.toString(),
                    password = etPassword.text.toString()
                )
                presenter.registerUser(user)

            }
        }
        binding.tvHaveAccount.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

    }
    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("REGISTER")
    }
}