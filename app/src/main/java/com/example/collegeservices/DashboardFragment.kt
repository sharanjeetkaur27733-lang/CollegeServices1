package com.example.collegeservices

import FeesFragment
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

        // 🔗 Connect cards
        feesCard = view.findViewById(R.id.feesCard)
        busCard = view.findViewById(R.id.busCard)
        leaveCard = view.findViewById(R.id.leaveCard)
        complaintCard = view.findViewById(R.id.complaintCard)
        noticeCard = view.findViewById(R.id.noticeCard)
        eventsCard = view.findViewById(R.id.eventsCard)

        // 🎯 Click Listeners

        feesCard.setOnClickListener {
            showToast("Fees Clicked")
            openFragment(FeesFragment())
        }

        busCard.setOnClickListener {
            showToast("Bus Clicked")
            openFragment(BusFragment())
        }

        leaveCard.setOnClickListener {
            showToast("Leave Clicked")
            openFragment(LeaveFragment())
        }

        complaintCard.setOnClickListener {
            showToast("Complaint Clicked")
            openFragment(ComplaintFragment())
        }

        noticeCard.setOnClickListener {
            showToast("Notice Clicked")
            openFragment(NoticesFragment())
        }

        eventsCard.setOnClickListener {
            showToast("Events Clicked")
            openFragment(EventsFragment())
        }

        return view
    }

    // ✅ Toast function (clean code)
    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    // 🔁 Fragment open function
    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null) // 🔙 back enable
            .commit()
    }
}