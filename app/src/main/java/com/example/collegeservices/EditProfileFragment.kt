package com.example.collegeservices

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EditProfileFragment : Fragment() {

    private lateinit var name: EditText
    private lateinit var className: EditText
    private lateinit var roll: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var updateBtn: Button

    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        name = view.findViewById(R.id.editName)
        className = view.findViewById(R.id.editClass)
        roll = view.findViewById(R.id.editRoll)
        email = view.findViewById(R.id.editEmail)
        password = view.findViewById(R.id.editPassword)
        confirmPassword = view.findViewById(R.id.editConfirmPassword)
        updateBtn = view.findViewById(R.id.updateBtn)

        auth = FirebaseAuth.getInstance()

        updateBtn.setOnClickListener {

            val userName = name.text.toString()
            val userClass = className.text.toString()
            val userRoll = roll.text.toString()
            val userEmail = email.text.toString()
            val userPass = password.text.toString()
            val userConfirm = confirmPassword.text.toString()

            // ✅ Validation
            if (userName.isEmpty() || userClass.isEmpty() || userRoll.isEmpty()
                || userEmail.isEmpty()) {

                Toast.makeText(requireContext(), "All fields required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userPass.isNotEmpty() && userPass != userConfirm) {
                Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = auth.currentUser?.uid

            val userMap = HashMap<String, Any>()
            userMap["name"] = userName
            userMap["class"] = userClass
            userMap["rollNo"] = userRoll
            userMap["email"] = userEmail

            if (userId != null) {

                // ✅ Update data (important: updateChildren use karo)
                database.child("Users").child(userId).updateChildren(userMap)
                    .addOnSuccessListener {

                        // ✅ Password update (optional)
                        if (userPass.isNotEmpty()) {
                            auth.currentUser?.updatePassword(userPass)
                        }

                        Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Update Failed", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        return view
    }
}