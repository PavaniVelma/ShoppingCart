package com.apolisb42.shoppingcart.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.ActivityShoppingCartBinding
import com.apolisb42.shoppingcart.view.ProductDetails.ProductDetailsFragment
import com.apolisb42.shoppingcart.view.SplashFragment
import com.apolisb42.shoppingcart.view.cart.CartFragment
import com.apolisb42.shoppingcart.view.products.ProductListFragment
import com.apolisb42.shoppingcart.view.subcategories.SubCategoryFragment

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShoppingCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavDrawer()
        navToSplash()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(supportFragmentManager.fragments.last() is SubCategoryFragment
            || supportFragmentManager.fragments.last() is ProductDetailsFragment
            || supportFragmentManager.fragments.last() is ProductListFragment ){
            if (item.itemId == android.R.id.home) {
                supportFragmentManager.popBackStack()
            }
        }
        else {
            if (item.itemId == android.R.id.home) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)


    }

    private fun initNavDrawer(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){

                R.id.cart ->supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CartFragment()).commit()

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    fun showBackButton(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
    }

    private fun navToSplash(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SplashFragment()).commit()
    }

     fun hideNavDrawer(){
         binding.toolbar.setNavigationIcon(null);          // to hide Navigation icon
         supportActionBar?.setDisplayHomeAsUpEnabled(false)
     }

    fun showNavDrawer(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
    }

    fun onChangeToolbarTitle(title:String){
        binding.tvTitleScreen.text = title
    }




}


