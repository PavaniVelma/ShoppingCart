package com.apolisb42.shoppingcart.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.ActivityShoppingCartBinding
import com.apolisb42.shoppingcart.view.SplashFragment

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

        if(item.itemId ==android.R.id.home ){
            if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)


    }

    private fun initNavDrawer(){
        setSupportActionBar(binding.toolbar)
        supportActionBar.apply {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun navToSplash(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SplashFragment()).commit()
    }




}


