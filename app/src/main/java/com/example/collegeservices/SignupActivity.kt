package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collegeservices.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnSignup.setOnClickListener {

            val name = binding.etName.text.toString().trim()
            val userClass = binding.etClass.text.toString().trim()
            val roll = binding.etRollNo.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPass = binding.etConfirmPassword.text.toString().trim()

            if (name.isEmpty() || userClass.isEmpty() || roll.isEmpty() ||
                email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()
            ) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val userId = auth.currentUser!!.uid

                    val userMap = mapOf(
                        "name" to name,
                        "class" to userClass,
                        "rollNo" to roll,
                        "email" to email
                    )

                    // 🔥 IMPORTANT: ensure data saved before moving
                    database.child("users").child(userId)
                        .setValue(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                            startActivity(Intent(this, BottomNavigationActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "DB Error: ${it.message}", Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Auth Error: ${it.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}