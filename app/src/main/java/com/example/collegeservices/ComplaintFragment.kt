package com.example.collegeservices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ComplaintFragment : Fragment() {

    private lateinit var submitComplaint: Button
    private lateinit var myComplaints: TextView
    private lateinit var backArrow: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_complaint, container, false)

        submitComplaint = view.findViewById(R.id.submitComplaint)
        myComplaints = view.findViewById(R.id.myComplaints)
        backArrow = view.findViewById(R.id.backArrow)

        // 🔙 Back Arrow
        backArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        submitComplaint.setOnClickListener {
            Toast.makeText(requireContext(), "Complaint Submitted", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}