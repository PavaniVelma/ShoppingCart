package com.apolisb42.shoppingcart.view.checkout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CheckoutViewPageAdapter(private val fragments: List<Fragment>, parentFragment: Fragment)
    : FragmentStateAdapter(parentFragment) {
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}
