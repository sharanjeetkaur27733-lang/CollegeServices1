package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

class NoticesFragment : Fragment() {

    private lateinit var backArrow: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_notices, container, false)

        backArrow = view.findViewById(R.id.backArrow)

        // 🔙 Back Arrow
        backArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        Toast.makeText(requireContext(), "Notices Loaded", Toast.LENGTH_SHORT).show()

        return view
    }
}