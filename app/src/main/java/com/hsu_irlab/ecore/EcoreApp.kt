package com.hsu_irlab.ecore

import android.app.Application
import com.hsu_irlab.ecore.util.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EcoreApp : Application(){
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        prefs=Prefs(applicationContext)
        super.onCreate()
    }

}