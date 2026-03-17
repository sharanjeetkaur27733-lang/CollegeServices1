package com.example.collegeservices

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomMenuActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_navigation)
        bottomNav = findViewById(R.id.bottom_nav)

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.dashboard -> {
                    Toast.makeText(this, "Dashboard Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.profile -> {
                    Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
}
