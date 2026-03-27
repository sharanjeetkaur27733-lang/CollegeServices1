package com.example.collegeservices

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class EditProfileFragment : Fragment() {

    private lateinit var name: EditText
    private lateinit var userClass: EditText
    private lateinit var rollNo: EditText
    private lateinit var email: EditText
    private lateinit var updateBtn: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        name = view.findViewById(R.id.editName)
        userClass = view.findViewById(R.id.editClass)
        rollNo = view.findViewById(R.id.editRoll)
        email = view.findViewById(R.id.editEmail)
        updateBtn = view.findViewById(R.id.updateBtn)

        val userId = auth.currentUser?.uid ?: return view

        // Load data
        database.child("users").child(userId)
            .get()
            .addOnSuccessListener {
                if (it.exists()) {
                    name.setText(it.child("name").value?.toString() ?: "")
                    userClass.setText(it.child("class").value?.toString() ?: "")
                    rollNo.setText(it.child("rollNo").value?.toString() ?: "")
                    email.setText(it.child("email").value?.toString() ?: "")
                }
            }

        // Update data
        updateBtn.setOnClickListener {
            val updatedMap = mapOf(
                "name" to name.text.toString(),
                "class" to userClass.text.toString(),
                "rollNo" to rollNo.text.toString(),
                "email" to email.text.toString()
            )

            database.child("users").child(userId)
                .updateChildren(updatedMap)
                .addOnSuccessListener {
                    auth.currentUser?.updateEmail(email.text.toString())
                    Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Update Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        return view
    }
}