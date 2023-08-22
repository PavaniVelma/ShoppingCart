package com.apolisb42.shoppingcart.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apolisb42.shoppingcart.databinding.FragmentAddAddressBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface AddAddressClickListener{
    fun onAddClicked(title:String, address:String)
}

class AddAddressFragment(val addAddressClickListener:AddAddressClickListener) : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentAddAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {

            val title = binding.etTitleAddress.text.toString()
            val address = binding.etTitleBody.text.toString()

            if(title.isNotEmpty() && address.isNotEmpty()) {

                addAddressClickListener.onAddClicked(title = title, address = address)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}