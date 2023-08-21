package com.apolisb42.shoppingcart.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.apolisb42.shoppingcart.R
import com.apolisb42.shoppingcart.databinding.ActivityShoppingCartBinding
import com.apolisb42.shoppingcart.model.database.AppDatabase
import com.apolisb42.shoppingcart.model.database.CartDao
import com.apolisb42.shoppingcart.model.util.UserProfileDetails
import com.apolisb42.shoppingcart.model.util.deleteStringFromSharedPreferences
import com.apolisb42.shoppingcart.view.authentication.ProfileFragment
import com.apolisb42.shoppingcart.view.cart.CartFragment
import com.apolisb42.shoppingcart.view.categories.CategoryFragment
import com.apolisb42.shoppingcart.view.checkout.OrderConfirmationFragment
import com.apolisb42.shoppingcart.view.orderslist.ListOfOrdersFragment

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShoppingCartBinding
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase
    private var isDarkTheme = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        initNavDrawer()
        navToSplash()
        toggleTheme()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if(supportFragmentManager.fragments.last() !is CategoryFragment
                && supportFragmentManager.fragments.last() !is CartFragment
                && supportFragmentManager.fragments.last() !is OrderConfirmationFragment
                && supportFragmentManager.fragments.last() !is ListOfOrdersFragment
                && supportFragmentManager.fragments.last() !is ProfileFragment)
            {
                supportFragmentManager.popBackStack()
            } else if (supportFragmentManager.fragments.last() is OrderConfirmationFragment){
                supportFragmentManager.popBackStack("CartFragment", POP_BACK_STACK_INCLUSIVE)
            }
            else {
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
                R.id.home -> {
                    supportFragmentManager.popBackStack("CartFragment", POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.popBackStack("ListOfOrdersFragment", POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.popBackStack("ProfileFragment", POP_BACK_STACK_INCLUSIVE)
                }
                R.id.cart -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CartFragment()).addToBackStack("CartFragment").commit()
                R.id.nightTheme -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.dayTheme -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.orders -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ListOfOrdersFragment()).addToBackStack("ListOfOrdersFragment").commit()
                R.id.profile -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProfileFragment()).addToBackStack("ProfileFragment").commit()
                R.id.logout ->{
                    val builder = AlertDialog.Builder(this)
                    builder.apply{

                        setTitle("Logout")
                        setMessage("Are you sure you want to logout.")
                        setPositiveButton("yes"){_,_->

                            cartDao.deleteCart()
                            deleteStringFromSharedPreferences("email")
                            deleteStringFromSharedPreferences("password")
                            finish()

                        }
                        setNegativeButton("no"){dialog,_->
                            dialog.dismiss()

                        }
                        setCancelable(false)
                    }
                    builder.create().show()
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    fun addHeaderDetails(){
        val headerView = binding.navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.name).text = UserProfileDetails.user?.full_name
        headerView.findViewById<TextView>(R.id.email).text = UserProfileDetails.user?.email_id
        headerView.findViewById<TextView>(R.id.phnNum).text = UserProfileDetails.user?.mobile_no
    }
    fun showBackButton(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
    }
    private fun navToSplash(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SplashFragment()).commit()
    }
    fun hideNavDrawer(){
         binding.toolbar.navigationIcon = null          // to hide Navigation icon
         supportActionBar?.setDisplayHomeAsUpEnabled(false)
     }
    fun showNavDrawer(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
    }
    fun onChangeToolbarTitle(title:String){
        binding.tvTitleScreen.text = title
    }
    private fun toggleTheme(){
        if(isDarkTheme){
            setTheme(R.style.ThemeLight)
        }
        setTheme(R.style.ThemeDark)
    }
    private fun initDatabase(){
        appDatabase = AppDatabase.getAppDatabase(this)
        cartDao = appDatabase.getCartDao()
    }
}


