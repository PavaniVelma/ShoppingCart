package com.apolisb42.shoppingcart.view
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentSplashBinding
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.getBooleanInSharedPreferences
import com.apolisb42.shoppingcart.model.util.getStringFromSharedPreferences
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.authentication.SplashPresenter
import com.apolisb42.shoppingcart.view.authentication.LoginFragment
import com.apolisb42.shoppingcart.view.categories.CategoryFragment
import com.apolisb42.shoppingcart.view.introscreen.IntroScreenFragment

class SplashFragment : Fragment() {

    private lateinit var binding:FragmentSplashBinding
    private lateinit var presenter:SplashPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                val emailId = activity?.getStringFromSharedPreferences("email")
                val passWord = activity?.getStringFromSharedPreferences("password")
                if (emailId?.isNotEmpty() == true && passWord?.isNotEmpty() == true){
                    presenter = SplashPresenter(VolleyHandler.getInstance(requireContext()),object:MVPShoppingCart.SplashView{
                        override fun setSuccess() {
                            (activity as? ShoppingCartActivity)?.addHeaderDetails()
                            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, CategoryFragment())?.commit()
                        }

                        override fun setError() {
                            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, LoginFragment())?.commit()
                        }

                    })
                    presenter.validateUser(emailId,passWord)
                }
                else {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, LoginFragment())?.commit()
                }

            }else{
                navToIntroScreen()
            }

        }, 1000)
    }

    private fun navToIntroScreen(){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IntroScreenFragment())?.commit()
    }

}