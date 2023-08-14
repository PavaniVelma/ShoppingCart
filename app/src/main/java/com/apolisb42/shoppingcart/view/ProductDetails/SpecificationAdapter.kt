package com.apolisb42.shoppingcart.view.ProductDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apolisb42.shoppingcart.databinding.ItemSpecificationsBinding
import com.apolisb42.shoppingcart.model.productdetailsmodel.Specification

class SpecificationAdapter(private val specificationList:List<Specification>):Adapter<SpecificationAdapter.SpecificationViewHolder>() {

    inner class SpecificationViewHolder(val binding:ItemSpecificationsBinding):ViewHolder(binding.root){
        fun bind(specification:Specification){
            with(binding){
                specificationTitle.text = specification.title
                specificationValue.text = specification.specification
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSpecificationsBinding.inflate(layoutInflater,parent,false)
        return SpecificationViewHolder(binding)
    }

    override fun getItemCount() = specificationList.size

    override fun onBindViewHolder(holder: SpecificationViewHolder, position: Int) {
        holder.bind(specificationList[position])
    }
}