package com.example.collegeservices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        // ✅ Default screen (Dashboard)
        loadFragment(DashboardFragment())

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    loadFragment(HomeFragment())   // 👈 agar hai
                    true
                }

                R.id.dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }

                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    // 🔁 Fragment change function
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}