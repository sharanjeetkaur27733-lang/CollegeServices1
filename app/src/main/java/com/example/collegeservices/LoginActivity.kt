package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collegeservices.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val userId = auth.currentUser!!.uid

                    // 🔥 Fetch data after login
                    database.child("users").child(userId)
                        .get()
                        .addOnSuccessListener {

                            if (it.exists()) {
                                val name = it.child("name").value?.toString() ?: ""

                                Toast.makeText(this, "Welcome $name", Toast.LENGTH_SHORT).show()

                                startActivity(Intent(this, BottomNavigationActivity::class.java))
                                finish()

                            } else {
                                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_LONG).show()
                }
        }

        binding.txtAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        // 🔥 Auto login
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
            finish()
        }
    }
}