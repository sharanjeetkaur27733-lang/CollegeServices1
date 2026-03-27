package com.example.collegeservices

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private lateinit var name: TextView
    private lateinit var userClass: TextView
    private lateinit var rollNo: TextView
    private lateinit var email: TextView
    private lateinit var editBtn: Button
    private lateinit var logoutBtn: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        name = view.findViewById(R.id.name)
        userClass = view.findViewById(R.id.course)
        rollNo = view.findViewById(R.id.rollNo)
        email = view.findViewById(R.id.email)
        editBtn = view.findViewById(R.id.editBtn)
        logoutBtn = view.findViewById(R.id.btnLogout)

        loadUserData()

        editBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, EditProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        return view
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid ?: return
        database.child("users").child(userId)
            .get()
            .addOnSuccessListener {
                if (it.exists()) {
                    name.text = it.child("name").value?.toString() ?: ""
                    userClass.text = it.child("class").value?.toString() ?: ""
                    rollNo.text = it.child("rollNo").value?.toString() ?: ""
                    email.text = it.child("email").value?.toString() ?: ""
                }
            }
    }
}