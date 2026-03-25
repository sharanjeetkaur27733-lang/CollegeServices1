package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ProfileFragment : Fragment() {

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var rollNo: TextView
    private lateinit var year: TextView
    private lateinit var editBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        name = view.findViewById(R.id.name)
        email = view.findViewById(R.id.email)
        rollNo = view.findViewById(R.id.rollNo)
        year = view.findViewById(R.id.year)
        editBtn = view.findViewById(R.id.editBtn)

        // Listen for results from EditProfileFragment
        parentFragmentManager.setFragmentResultListener("editProfile", viewLifecycleOwner) { _, bundle ->
            val newName = bundle.getString("name")
            val newEmail = bundle.getString("email")
            if (!newName.isNullOrEmpty()) name.text = newName
            if (!newEmail.isNullOrEmpty()) email.text = newEmail
        }

        editBtn.setOnClickListener {
            // Pass current values to EditProfileFragment using arguments
            val editFragment = EditProfileFragment().apply {
                arguments = Bundle().apply {
                    putString("name", name.text.toString())
                    putString("email", email.text.toString())
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}