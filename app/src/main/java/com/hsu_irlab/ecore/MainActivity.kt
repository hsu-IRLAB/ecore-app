package com.hsu_irlab.ecore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.activity_main_container) as NavHostFragment
        navController = navHomeFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_main_navi)
        //임시로 토큰넣
        EcoreApp.prefs.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjo4NiwiTkFNRSI6Im5hbmFuYW1lIiwiaWF0IjoxNjYwMTExODc1LCJleHAiOjE2ODYwMzE4NzUsImlzcyI6IkVDT1JFIn0.1lhRXwjfir76goRLAJOJlHdxzBbquQzaAwq7WvsCJQY"
        EcoreApp.prefs.user_id=86

        setupWithNavController(bottomNavigationView,navController)
    }
}