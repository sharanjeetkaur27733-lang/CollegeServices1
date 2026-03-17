package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etClass: EditText
    lateinit var etRollNo: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var btnSignup: Button
    lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        // 🔗 Connect XML IDs
        etName = findViewById(R.id.etName)
        etClass = findViewById(R.id.etClass)
        etRollNo = findViewById(R.id.etrollno)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSignup = findViewById(R.id.btnSignup)
        tvLogin = findViewById(R.id.tvLogin)

        // ✅ Signup Button Click
        btnSignup.setOnClickListener {

            val name = etName.text.toString()
            val studentClass = etClass.text.toString()
            val rollNo = etRollNo.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (name.isEmpty() || studentClass.isEmpty() || rollNo.isEmpty()
                || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
            else if (password != confirmPassword) {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // 🔁 Login Text Click
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}