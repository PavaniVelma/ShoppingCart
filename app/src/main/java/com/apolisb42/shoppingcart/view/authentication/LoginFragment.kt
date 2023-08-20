package com.apolisb42.shoppingcart.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentLoginBinding
import com.apolisb42.shoppingcart.model.network.VolleyHandler
import com.apolisb42.shoppingcart.model.util.putStringInSharedPreference
import com.apolisb42.shoppingcart.presenter.MVPShoppingCart
import com.apolisb42.shoppingcart.presenter.authentication.LoginPresenter
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.apolisb42.shoppingcart.view.categories.CategoryFragment


class LoginFragment : Fragment() {

    private lateinit var binding:FragmentLoginBinding
    private lateinit var loginPresenter: LoginPresenter

   



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.hideNavDrawer()
        (activity as? ShoppingCartActivity)?.supportActionBar?.show()


        loginPresenter = LoginPresenter(VolleyHandler.getInstance(requireContext()), object:MVPShoppingCart.LoginView{
            override fun setError() {


            }

            override fun setSuccess() {
                (activity as? ShoppingCartActivity)?.addHeaderDetails()
                activity?.putStringInSharedPreference("email",binding.etEmail.text.toString())
                activity?.putStringInSharedPreference("password", binding.etPassword.text.toString())
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,CategoryFragment())?.commit()
            }
        })
        binding.button.setOnClickListener {
            loginPresenter.validateUser(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.tvNotHaveAccount.setOnClickListener {
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container,RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("LOGIN")
    }




}