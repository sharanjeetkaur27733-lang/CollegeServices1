package com.example.collegeservices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        // 1. NavHostFragment ko find karein (Ye wahi hai jo activity_bottom_navigation.xml mein hai)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // 2. NavController nikaalein
        val navController = navHostFragment.navController

        // 3. BottomNav ko NavController ke saath jod dein
        // Ab aapko manually fragment load karne ki zarurat nahi hai!
        // Ye line khud check karegi ki click hone par kaunsa fragment dikhana hai.
        bottomNav.setupWithNavController(navController)
    }
}