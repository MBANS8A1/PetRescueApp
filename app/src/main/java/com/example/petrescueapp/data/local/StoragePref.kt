package com.example.petrescueapp.data.local

import android.content.Context
import com.example.petrescueapp.R
import com.example.petrescueapp.data.network.models.baseObject

class StoragePref(context:Context) {
    private val pref = context
        .getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )

    fun getToken():String =
        pref.getString(baseObject.USER_TOKEN,null) ?: ""

    fun saveToken(token:String?){
        pref.edit().apply {
            putString(baseObject.USER_TOKEN,token)
                .apply()
        }
    }
}