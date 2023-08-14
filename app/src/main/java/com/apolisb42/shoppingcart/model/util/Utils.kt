package com.apolisb42.shoppingcart.model.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


fun Context.getSharedPreference(): SharedPreferences {
    return getSharedPreferences(Constants.DEMO_SHARED_PREF, MODE_PRIVATE)
}

fun Context.putStringInSharedPreference(key:String, value:String){
    getSharedPreference().edit().putString(key, value).apply()
}

fun Context.getStringFromSharedPreferences(key:String):String{
    return  getSharedPreference().getString(key, "") ?: ""
}