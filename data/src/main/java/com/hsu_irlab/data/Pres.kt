package com.hsu_irlab.data

import android.content.SharedPreferences

class Prefs(private val prefs: SharedPreferences) {
    var jwt:String
        get() = prefs.getString("JWT_TOKEN", "NO_TOKEN")?:"NO_TOKEN"
        set(jwt:String){
            prefs.edit().putString("JWT_TOKEN",jwt).apply()
        }
}