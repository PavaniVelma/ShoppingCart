package com.apolisb42.shoppingcart.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentSplashBinding
import com.apolisb42.shoppingcart.model.util.getBooleanInSharedPreferences
import com.apolisb42.shoppingcart.model.util.getStringFromSharedPreferences
import com.apolisb42.shoppingcart.model.util.putBooleanInSharedPreferences
import com.apolisb42.shoppingcart.model.util.putStringInSharedPreference
import com.apolisb42.shoppingcart.view.authentication.LoginFragment
import com.apolisb42.shoppingcart.view.categories.CategoryFragment
import com.apolisb42.shoppingcart.view.checkout.CartItemsFragment
import com.apolisb42.shoppingcart.view.introscreen.IntroScreenFragment

class SplashFragment : Fragment() {

    private lateinit var binding:FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSplash()
        (activity as? ShoppingCartActivity)?.hideNavDrawer()
        (activity as? ShoppingCartActivity)?.supportActionBar?.hide()
    }

    private fun initSplash(){

        Handler(Looper.getMainLooper()).postDelayed({


            if(activity?.getBooleanInSharedPreferences(key = "firstTime", value = false) == true){
                if (activity?.getStringFromSharedPreferences("email")?.isNotEmpty() == true)
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, CartItemsFragment())?.commit()
                else
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, LoginFragment())?.commit()

            }else{
                navToIntroScreen()
            }

        }, 1000)
    }

    private fun navToIntroScreen(){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IntroScreenFragment())?.commit()
    }

}