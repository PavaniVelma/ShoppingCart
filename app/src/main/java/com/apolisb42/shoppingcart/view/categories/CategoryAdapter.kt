package com.apolisb42.shoppingcart.view.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apolisb42.shoppingcart.databinding.CategoriesItemBinding
import com.apolisb42.shoppingcart.model.categories.Category
import com.apolisb42.shoppingcart.model.network.VolleyConstants
import com.squareup.picasso.Picasso

class CategoryAdapter(val categoryList:List<Category>, val itemClickListener: ItemClickListener):Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding:CategoriesItemBinding):ViewHolder(binding.root){
        fun bind(category:Category){
            with(binding){
                tvCategoryName.text = category.category_name
                val image = "${VolleyConstants.IMAGE_URL}${category.category_image_url}"
                Picasso.get().load(image).into(categoryImage)
                binding.root.setOnClickListener {
                    itemClickListener.isSelected(category.category_id)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoriesItemBinding.inflate(layoutInflater)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }
}