package com.example.yourappname

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.collegeservices.R
import com.example.collegeservices.SignupActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<TextInputEditText>(R.id.etEmail)
        val password = findViewById<TextInputEditText>(R.id.etPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val signupText = findViewById<TextView>(R.id.txtAccount)

        // 👉 Login Button Click
        loginBtn.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                email.error = "Enter Email"
                password.error = "Enter Password"
            } else {
                // 👉 yaha future me Firebase login lagayenge
                // Abhi simple next screen open kara dete hain
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
        }

        // 👉 Signup Text Click
        signupText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}