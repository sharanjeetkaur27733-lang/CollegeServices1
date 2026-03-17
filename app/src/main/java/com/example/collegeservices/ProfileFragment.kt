package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ProfileFragment : Fragment() {

    private lateinit var profileImg: ImageView
    private lateinit var name: TextView
    private lateinit var editBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Connect views
        profileImg = view.findViewById(R.id.profileImg)
        name = view.findViewById(R.id.name)
        editBtn = view.findViewById(R.id.editBtn)

        // Button click
        editBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Edit Profile Clicked", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}