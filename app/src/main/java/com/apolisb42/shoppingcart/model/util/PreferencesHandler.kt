package com.apolisb42.shoppingcart.model.util

import android.content.Context

class PreferencesHandler(val context: Context) {
    fun putUserDetailsInPreferences(userProfile: UserProfile){
        with(userProfile){
            context.putStringInSharedPreference(Constants.USER_NAME, userName)
            context.putStringInSharedPreference(Constants.USER_MOBILE_NUM, phnNum)
            context.putStringInSharedPreference(Constants.USER_EMAIL, emailId)
            context.putStringInSharedPreference(Constants.USER_PASSWORD, password)
        }

    }

    fun authenticateUser(email:String, password:String):Boolean{
        val userEmailFromPref = context.getStringFromSharedPreferences(Constants.USER_EMAIL)
        val passwordFromPref = context.getStringFromSharedPreferences(Constants.USER_PASSWORD)

        return email == userEmailFromPref && password==passwordFromPref

    }



}