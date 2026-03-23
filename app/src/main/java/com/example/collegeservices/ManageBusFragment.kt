package com.example.collegeservices

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase

class ManageBusFragment : Fragment() {

    private lateinit var listView: ListView
    private val list = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_manage_bus, container, false)

        listView = view.findViewById(R.id.listView)

        loadData()

        return view
    }

    private fun loadData() {

        FirebaseDatabase.getInstance().getReference("BusRegistration")
            .get()
            .addOnSuccessListener { snapshot ->

                list.clear()

                for (data in snapshot.children) {

                    val route = data.child("route").value.toString()
                    val fee = data.child("fee").value.toString()
                    val status = data.child("status").value.toString()

                    val text = "Route: $route | Fee: ₹$fee | Status: $status"

                    list.add(text)
                }

                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    list
                )

                listView.adapter = adapter
            }
    }
}