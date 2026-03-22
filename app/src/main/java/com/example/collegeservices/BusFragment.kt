package com.example.collegeservices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BusFragment : Fragment() {

    private lateinit var routeSpinner: Spinner
    private lateinit var busFee: TextView
    private lateinit var paymentStatus: TextView
    private lateinit var registerBusBtn: Button
    private lateinit var backArrow: ImageView

    private val routes = listOf("Select Route", "Route A", "Route B")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_bus, container, false)

        // 🔗 XML IDs connect karo
        routeSpinner = view.findViewById(R.id.routeSpinner)
        busFee = view.findViewById(R.id.busFee)
        paymentStatus = view.findViewById(R.id.paymentStatus)
        registerBusBtn = view.findViewById(R.id.registerBusBtn)
        backArrow = view.findViewById(R.id.backArrow)

        setupSpinner()
        setupButton()
        setupBack()

        return view
    }

    // 🔽 Spinner setup
    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            routes
        )
        routeSpinner.adapter = adapter

        routeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                when (position) {
                    1 -> {
                        busFee.text = "₹ 5,000"
                        paymentStatus.text = "Unpaid"
                    }
                    2 -> {
                        busFee.text = "₹ 6,000"
                        paymentStatus.text = "Unpaid"
                    }
                    else -> {
                        busFee.text = "₹ 0"
                        paymentStatus.text = "-"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    // 🔘 Button logic
    private fun setupButton() {
        registerBusBtn.setOnClickListener {

            val route = routeSpinner.selectedItem.toString()

            if (route == "Select Route") {
                Toast.makeText(requireContext(), "Select route first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveData(route)
        }
    }

    // ☁ Firebase save
    private fun saveData(route: String) {

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val fee = if (route == "Route A") "5000" else "6000"

        val data = HashMap<String, String>()
        data["route"] = route
        data["fee"] = fee
        data["status"] = "unpaid"

        FirebaseDatabase.getInstance().getReference("BusRegistration")
            .child(userId)
            .setValue(data)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Bus Registered Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    // 🔙 Back button
    private fun setupBack() {
        backArrow.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                requireActivity().finish()
            }
        }
    }
}