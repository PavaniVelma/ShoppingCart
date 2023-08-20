package com.apolisb42.shoppingcart.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apolisb42.shoppingcart.databinding.FragmentProfileBinding
import com.apolisb42.shoppingcart.model.util.UserProfileDetails


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUserEmail.text = UserProfileDetails.user?.email_id
        binding.tvUserName.text = UserProfileDetails.user?.full_name
    }
}