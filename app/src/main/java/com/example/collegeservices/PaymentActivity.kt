package com.example.collegeservices

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val payBtn = findViewById<Button>(R.id.confirmPayBtn)

        payBtn.setOnClickListener {
            Toast.makeText(this, "Payment Successful ✅", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}