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

fun Context.deleteStringFromSharedPreferences(key:String){
    getSharedPreference().edit().remove(key).apply()
}

fun Context.putBooleanInSharedPreferences(key:String,value:Boolean){
    getSharedPreference().edit().putBoolean(key, value).apply()
}

fun Context.getBooleanInSharedPreferences(key:String,value:Boolean):Boolean{
    return getSharedPreference().getBoolean(key,value)
}