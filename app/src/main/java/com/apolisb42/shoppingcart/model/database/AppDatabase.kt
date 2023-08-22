package com.apolisb42.shoppingcart.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apolisb42.shoppingcart.model.cart.CartItem
import com.apolisb42.shoppingcart.model.database.DatabaseConstants.DATABASE_NAME

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase():RoomDatabase() {
 abstract fun getCartDao():CartDao
 companion object{
  private var INSTANCE:AppDatabase?=null

  fun getAppDatabase(context: Context):AppDatabase{

   if(INSTANCE == null){
    INSTANCE = Room.databaseBuilder(
     context,
     AppDatabase::class.java,
     DATABASE_NAME,
     ).allowMainThreadQueries().build()
   }
   return INSTANCE as AppDatabase
  }
 }
}