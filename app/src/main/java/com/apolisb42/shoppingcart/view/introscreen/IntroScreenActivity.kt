package com.apolisb42.shoppingcart.view.introscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apolisb42.shoppingcart.databinding.ActivityIntroScreenBinding
import com.apolisb42.shoppingcart.databinding.ItemDotBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivityIntroScreenBinding
    private lateinit var viewPageAdapter: ViewPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews(){
        val fragments = listOf<Fragment>(PlaceOrderFragment(), ProductFragment(), CartFragment())
        viewPageAdapter = ViewPageAdapter(fragments, this@IntroScreenActivity)

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


