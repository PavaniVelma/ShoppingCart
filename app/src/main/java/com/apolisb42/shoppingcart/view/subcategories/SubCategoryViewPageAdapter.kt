package com.apolisb42.shoppingcart.view.subcategories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SubCategoryViewPageAdapter(private val fragments:List<Fragment>, fragmentActivity: FragmentActivity)
    :FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
}