package com.hsu_irlab.ecore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        setupWithNavController(bottomNavigationView,navController)
    }
}