package com.example.collegeservices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class EventsFragment : Fragment() {

    private lateinit var backBtn: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_events, container, false)

        // correct way
        backBtn = view.findViewById(R.id.backBtn)

        backBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Back clicked", Toast.LENGTH_SHORT).show()

            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return view
    }
}