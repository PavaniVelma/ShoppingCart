package com.apolisb42.shoppingcart.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.apolisb42.shoppingcart.databinding.QuantityStepperBinding

interface QuantityStepperListener{
    fun onQuantityChanged(quantity: Int)
    fun onQuantityZero()
}
class QuantityStepper(context: Context, attrs: AttributeSet):ConstraintLayout(context, attrs) {

    private var quantityStepperListener: QuantityStepperListener? = null
    private val binding: QuantityStepperBinding
    private var quantity = 1

    init {
        binding = QuantityStepperBinding.inflate(LayoutInflater.from(getContext()), this)
        with(binding){
            imgAdd.setOnClickListener {
                quantity++
                tvQuantity.text = (quantity).toString()
                quantityStepperListener?.onQuantityChanged(quantity)
            }
            imgRemove.setOnClickListener {
                quantity--
                if(quantity == 0){
                    quantity = 1
                    tvQuantity.text = 1.toString()
                    quantityStepperListener?.onQuantityZero()

                } else{
                    tvQuantity.text = quantity.toString()
                    quantityStepperListener?.onQuantityChanged(quantity)
                }
            }
        }
    }

    fun setQuantity(quantity: Int){
        this.quantity = quantity
        binding. tvQuantity.text = quantity.toString()
    }

    fun setQuantityStepperListener(quantityStepperListener: QuantityStepperListener){
        this.quantityStepperListener = quantityStepperListener
    }



}