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

                R.id.Home -> {
                    loadFragment(HomeFragment())   // 👈 agar hai
                    true
                }

                R.id.Dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }

                R.id.Profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                R.id.AdminfragmentadminFragment -> {
                    loadFragment(AdminFragment())
                    true
                }


                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}