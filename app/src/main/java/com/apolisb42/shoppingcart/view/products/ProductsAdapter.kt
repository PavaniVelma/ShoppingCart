package com.apolisb42.shoppingcart.view.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apolisb42.shoppingcart.databinding.ProductsItemBinding
import com.apolisb42.shoppingcart.model.network.VolleyConstants.Companion.IMAGE_URL
import com.apolisb42.shoppingcart.model.productslist.Product
import com.apolisb42.shoppingcart.view.categories.ItemClickListener
import com.squareup.picasso.Picasso

class ProductsAdapter(private val products:List<Product>,val itemClickListener: ItemClickListener):Adapter<ProductsAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(val binding:ProductsItemBinding):ViewHolder(binding.root){
        fun bind(product:Product){
            with(binding){
                productName.text = product.product_name
                productDescription.text = product.description
                productPrice.text = "$ ${product.price}"
                ratingBar.rating = product.average_rating.toFloat()
                val url = "$IMAGE_URL${product.product_image_url}"
                Picasso.get().load(url).into(imagePhone)

                binding.root.setOnClickListener {
                    itemClickListener.isSelected(product.product_id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductsItemBinding.inflate(layoutInflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
}