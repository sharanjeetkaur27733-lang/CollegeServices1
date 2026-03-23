package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import android.widget.Toast

class AdminFragment : Fragment() {

    private lateinit var studentsCard: CardView
    private lateinit var feesCard: CardView
    private lateinit var busCard: CardView
    private lateinit var complaintCard: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_admin, container, false)


        // Connect cards
        studentsCard = view.findViewById(R.id.studentsCard)
        feesCard = view.findViewById(R.id.feesCard)
        busCard = view.findViewById(R.id.busCard)
        complaintCard = view.findViewById(R.id.complaintCard)

        // Clicks
        studentsCard.setOnClickListener {
            Toast.makeText(requireContext(), "Manage Students", Toast.LENGTH_SHORT).show()
        }

        feesCard.setOnClickListener {
            Toast.makeText(requireContext(), "Manage Fees", Toast.LENGTH_SHORT).show()
        }

        busCard.setOnClickListener {
            Toast.makeText(requireContext(), "Manage Bus", Toast.LENGTH_SHORT).show()
        }

        complaintCard.setOnClickListener {
            Toast.makeText(requireContext(), "Manage Complaints", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}