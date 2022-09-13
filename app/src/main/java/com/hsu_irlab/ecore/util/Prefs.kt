package com.hsu_irlab.ecore.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val jwtToken="JWT_TOKKEN"
    private val prefs=context.getSharedPreferences(jwtToken, MODE_PRIVATE)

    var token:String?
        get() = prefs.getString("token",null)
        set(value){
            prefs.edit().putString("token",value).apply()
        }

}

