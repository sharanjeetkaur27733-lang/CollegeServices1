package com.example.collegeservices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FeesFragment : Fragment() {

    private lateinit var payBtn: Button
    private lateinit var historyBtn: Button
    private lateinit var backArrow: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_fees, container, false)

        payBtn = view.findViewById(R.id.payBtn)
        historyBtn = view.findViewById(R.id.historyBtn)
        backArrow = view.findViewById(R.id.backArrow)

        // 🔙 Back Arrow
        backArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        payBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Payment feature coming soon", Toast.LENGTH_SHORT).show()
        }

        historyBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Viewing history", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}