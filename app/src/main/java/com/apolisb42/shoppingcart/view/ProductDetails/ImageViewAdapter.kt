package com.apolisb42.shoppingcart.view.ProductDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager.widget.PagerAdapter
import com.apolisb42.shoppingcart.databinding.ItemImagesBinding
import com.apolisb42.shoppingcart.model.network.VolleyConstants
import com.apolisb42.shoppingcart.model.productdetailsmodel.Image
import com.squareup.picasso.Picasso


class ImageViewAdapter(private val images:List<Image>, val context: Context): Adapter<ImageViewAdapter.ImageViewHolder>() {




    override fun onCreateViewHolder(container: ViewGroup, position: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemImagesBinding.inflate(layoutInflater, container, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount()= images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int){
        holder.bind(images[position])
    }


    inner class ImageViewHolder(val binding:ItemImagesBinding):ViewHolder(binding.root){
        fun bind(image:Image){
            Picasso.get().load("${VolleyConstants.IMAGE_URL}${image.image}").into(binding.imageFlipper)

        }
    }
}

