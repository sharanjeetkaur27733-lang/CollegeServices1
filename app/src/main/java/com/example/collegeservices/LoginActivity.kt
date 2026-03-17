package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button
    lateinit var signup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // 🔗 Connect XML
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        loginBtn = findViewById(R.id.btnLogin)
        signup = findViewById(R.id.txtAccount)   // ✅ Correct ID

        // ✅ Login Button
        loginBtn.setOnClickListener {

            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // 👉 Open Dashboard Activity (NOT Fragment)
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // ✅ Signup Text Click
        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}