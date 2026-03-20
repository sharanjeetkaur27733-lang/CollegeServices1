package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collegeservices.BottomNavigationActivity
import com.example.collegeservices.R
import com.google.android.material.textfield.TextInputEditText

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val name = findViewById<TextInputEditText>(R.id.etName)
        val className = findViewById<TextInputEditText>(R.id.etClass)
        val rollNo = findViewById<TextInputEditText>(R.id.etRollNo)
        val email = findViewById<TextInputEditText>(R.id.etEmail)
        val password = findViewById<TextInputEditText>(R.id.etPassword)
        val confirmPassword = findViewById<TextInputEditText>(R.id.etConfirmPassword)
        val signupBtn = findViewById<Button>(R.id.btnSignup)

        signupBtn.setOnClickListener {

            val userName = name.text.toString().trim()
            val userClass = className.text.toString().trim()
            val userRoll = rollNo.text.toString().trim()
            val userEmail = email.text.toString().trim()
            val userPass = password.text.toString().trim()
            val userConfirmPass = confirmPassword.text.toString().trim()

            // ✅ Empty Validation
            if (userName.isEmpty() ||
                userClass.isEmpty() ||
                userRoll.isEmpty() ||
                userEmail.isEmpty() ||
                userPass.isEmpty() ||
                userConfirmPass.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            // ✅ Password Match Check
            else if (userPass != userConfirmPass) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            }

            else {
                // 👉 Future: yaha Firebase / Database add kar sakta hai

                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                // ✅ Move to Bottom Navigation Screen
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}