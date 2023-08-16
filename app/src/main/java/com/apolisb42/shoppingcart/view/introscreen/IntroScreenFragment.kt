package com.apolisb42.shoppingcart.view.introscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.FragmentIntroScreenBinding
import com.apolisb42.shoppingcart.databinding.ItemDotBinding
import com.apolisb42.shoppingcart.view.ShoppingCartActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroScreenFragment : Fragment() {

    private lateinit var binding:FragmentIntroScreenBinding
    private lateinit var viewPageAdapter: ViewPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroScreenBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setDisplayShowHomeEnabled(false)
        (activity as? ShoppingCartActivity)?.supportActionBar?.show()
        initViews()
    }

    private fun initViews(){
        val fragments = listOf<Fragment>(PlaceOrderFragment(), ProductFragment(), CartFragment())
        viewPageAdapter = ViewPageAdapter(fragments, requireActivity())

        with(binding){
            viewPager2.adapter = viewPageAdapter
            TabLayoutMediator(tabLayout, viewPager2){ tab: TabLayout.Tab, i: Int ->

                tab.customView =createTabView() }.attach()

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    updateTabDots(position)
                }
            })

        }
    }
    private fun createTabView(): View {
        val tabBinding = ItemDotBinding.inflate(layoutInflater)
        return tabBinding.root
    }

    private fun updateTabDots(selectedPosition:Int){

        for(i in 0 until binding.tabLayout.tabCount){
            val tabView = binding.tabLayout.getTabAt(i)?.customView
            val tabBinding = tabView?.let { ItemDotBinding.bind(it) }

            if (i == selectedPosition) {
                tabBinding?.imgDotSelected?.visibility = View.VISIBLE
                tabBinding?.imgDotUnselected?.visibility = View.GONE
            } else {
                tabBinding?.imgDotSelected?.visibility = View.GONE
                tabBinding?.imgDotUnselected?.visibility = View.VISIBLE
            }

        }
    }
}