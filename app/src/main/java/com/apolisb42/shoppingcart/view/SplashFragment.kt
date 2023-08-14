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
        activity?.actionBar?.setDisplayShowHomeEnabled(false)
    }

    private fun initSplash(){

        Handler(Looper.getMainLooper()).postDelayed({
            navToIntroScreen()
        }, 1000)
    }

    private fun navToIntroScreen(){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IntroScreenFragment())?.commit()
    }

}