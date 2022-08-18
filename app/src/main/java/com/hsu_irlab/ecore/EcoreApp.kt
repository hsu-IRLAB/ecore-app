package com.hsu_irlab.ecore

import android.app.Application
import com.hsu_irlab.ecore.util.Prefs

class EcoreApp : Application(){
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        prefs=Prefs(applicationContext)
        super.onCreate()
    }

}