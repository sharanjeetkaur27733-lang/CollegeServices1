package com.example.collegeservices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditProfileFragment : Fragment() {

    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var saveBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        // Connect views
        editName = view.findViewById(R.id.editName)
        editEmail = view.findViewById(R.id.editEmail)
        saveBtn = view.findViewById(R.id.saveBtn)

        // Pre-fill the fields using arguments from ProfileFragment
        arguments?.getString("name")?.let { editName.setText(it) }
        arguments?.getString("email")?.let { editEmail.setText(it) }

        // Save button click
        saveBtn.setOnClickListener {
            val newName = editName.text.toString().trim()
            val newEmail = editEmail.text.toString().trim()

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Send the updated data back to ProfileFragment
            val result = Bundle().apply {
                putString("name", newName)
                putString("email", newEmail)
            }
            parentFragmentManager.setFragmentResult("editProfile", result)

            Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()

            // Go back to ProfileFragment
            parentFragmentManager.popBackStack()
        }

        return view
    }
}