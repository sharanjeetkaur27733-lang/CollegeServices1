package com.example.collegeservices

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val listView = findViewById<ListView>(R.id.historyList)

        val history = arrayOf(
            "₹ 5000 - Paid (Jan)",
            "₹ 5000 - Paid (Feb)",
            "₹ 5000 - Paid (Mar)"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, history)
        listView.adapter = adapter
    }
}