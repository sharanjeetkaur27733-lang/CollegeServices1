package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class BusFragment : Fragment() {

    private lateinit var route: TextView
    private lateinit var registerBusBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_bus, container, false)

        // Connect views
        route = view.findViewById(R.id.route)
        registerBusBtn = view.findViewById(R.id.registerBusBtn)

        // Route click (optional)
        route.setOnClickListener {
            Toast.makeText(requireContext(), "Select Route Clicked", Toast.LENGTH_SHORT).show()
        }

        // Register button click
        registerBusBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Bus Registered", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}