package com.apolisb42.shoppingcart.view.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.apolisb42.shoppingcart.databinding.ItemCartBinding
import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.network.VolleyConstants
import com.apolisb42.shoppingcart.view.categories.ItemClickListener
import com.squareup.picasso.Picasso

class CartAdapter(private val cartList:List<CartItem>, val itemClickListener: ItemClickListener,private val showQuantityStepper: Boolean = true):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(cartItem: CartItem){
            with(binding){
                productName.text = cartItem.product_name
                productDescription.text = cartItem.description
                productPrice.text = "$ ${cartItem.unitPrice}"
                tvPriceTotal.text = "$ ${cartItem.price}"
                quantityStepper.setQuantity(cartItem.quantity)
                quantityStepper.isVisible = showQuantityStepper
                val url = "${VolleyConstants.IMAGE_URL}${cartItem.product_image_url}"
                Picasso.get().load(url).into(imagePhone)
                binding.root.setOnClickListener {
                    itemClickListener.isSelected(cartItem.id)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(layoutInflater,parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }
}