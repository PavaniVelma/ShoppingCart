package com.apolisb42.shoppingcart.view.checkout

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolisb42.shoppingcart.databinding.ItemCheckoutCartBinding
import com.apolisb42.shoppingcart.model.checkout.Item
import com.apolisb42.shoppingcart.model.network.VolleyConstants
import com.squareup.picasso.Picasso

class OrderItemsAdapter(private val orderList: List<Item>): RecyclerView.Adapter<OrderItemsAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(val binding: ItemCheckoutCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(orderItem: Item) {
            with(binding) {
                productName.text = orderItem.product_name
                productPrice.text = "$ ${orderItem.amount}"
                productUnitPrice.text = orderItem.unit_price
                productQuantity.text = orderItem.quantity
                val url = "${VolleyConstants.IMAGE_URL}${orderItem.product_image_url}"
                Picasso.get().load(url).into(imagePhone)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckoutCartBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount() = orderList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(orderList[position])
    }
}