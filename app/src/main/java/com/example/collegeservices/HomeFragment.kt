package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class HomeFragment : Fragment() {

    private lateinit var startBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Connect button
        startBtn = view.findViewById(R.id.startBtn)

        // Button click
        startBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Welcome to College App", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}