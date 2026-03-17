package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ComplaintFragment : Fragment() {

    private lateinit var submitComplaint: Button
    private lateinit var myComplaints: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_complaint, container, false)

        // Connect views
        submitComplaint = view.findViewById(R.id.submitComplaint)
        myComplaints = view.findViewById(R.id.myComplaints)

        // Button click
        submitComplaint.setOnClickListener {
            Toast.makeText(requireContext(), "Complaint Submitted", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}