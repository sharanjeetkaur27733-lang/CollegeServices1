package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import android.widget.Toast

class DashboardFragment : Fragment() {

    private lateinit var feesCard: CardView
    private lateinit var busCard: CardView
    private lateinit var leaveCard: CardView
    private lateinit var complaintCard: CardView
    private lateinit var noticeCard: CardView
    private lateinit var eventsCard: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Connect cards
        feesCard = view.findViewById(R.id.feesCard)
        busCard = view.findViewById(R.id.busCard)
        leaveCard = view.findViewById(R.id.leaveCard)
        complaintCard = view.findViewById(R.id.complaintCard)
        noticeCard = view.findViewById(R.id.noticeCard)
        eventsCard = view.findViewById(R.id.eventsCard)

        // Clicks
        feesCard.setOnClickListener {
            Toast.makeText(requireContext(), "Fees Clicked", Toast.LENGTH_SHORT).show()
        }

        busCard.setOnClickListener {
            Toast.makeText(requireContext(), "Bus Clicked", Toast.LENGTH_SHORT).show()
        }

        leaveCard.setOnClickListener {
            Toast.makeText(requireContext(), "Leave Clicked", Toast.LENGTH_SHORT).show()
        }

        complaintCard.setOnClickListener {
            Toast.makeText(requireContext(), "Complaint Clicked", Toast.LENGTH_SHORT).show()
        }

        noticeCard.setOnClickListener {
            Toast.makeText(requireContext(), "Notices Clicked", Toast.LENGTH_SHORT).show()
        }

        eventsCard.setOnClickListener {
            Toast.makeText(requireContext(), "Events Clicked", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}