package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collegeservices.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // 👉 Go to Login
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // 👉 Signup Button
        binding.btnSignup.setOnClickListener {

            val userName = binding.etName.text.toString().trim()
            val userClass = binding.etClass.text.toString().trim()
            val userRoll = binding.etRollNo.text.toString().trim()
            val userEmail = binding.etEmail.text.toString().trim()
            val userPass = binding.etPassword.text.toString().trim()
            val userConfirmPass = binding.etConfirmPassword.text.toString().trim()

            // ✅ Empty Check
            if (userName.isEmpty() ||
                userClass.isEmpty() ||
                userRoll.isEmpty() ||
                userEmail.isEmpty() ||
                userPass.isEmpty() ||
                userConfirmPass.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            // ✅ Password Match
            else if (userPass != userConfirmPass) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            }

            else {

                // 🔥 Firebase Signup
                auth.createUserWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                            // ✅ Open Bottom Navigation Activity
                            val intent = Intent(this, BottomNavigationActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(
                                this,
                                "Error: ${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}