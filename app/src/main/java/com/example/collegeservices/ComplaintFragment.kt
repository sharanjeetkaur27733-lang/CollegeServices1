package com.example.collegeservices

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

class ComplaintFragment : Fragment() {

    private lateinit var input: EditText
    private lateinit var submitBtn: Button
    private lateinit var listView: ListView
    private lateinit var pendingBtn: Button
    private lateinit var resolvedBtn: Button

    private lateinit var database: DatabaseReference

    private var complaintList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    private var currentFilter = "pending" // default

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_complaint, container, false)

        input = view.findViewById(R.id.complaintInput)
        submitBtn = view.findViewById(R.id.submitComplaint)
        listView = view.findViewById(R.id.complaintList)
        pendingBtn = view.findViewById(R.id.pendingBtn)
        resolvedBtn = view.findViewById(R.id.resolvedBtn)

        database = FirebaseDatabase.getInstance().reference.child("complaints")

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, complaintList)
        listView.adapter = adapter

        // Submit Complaint
        submitBtn.setOnClickListener {
            val text = input.text.toString().trim()

            if (text.isEmpty()) {
                Toast.makeText(requireContext(), "Enter complaint", Toast.LENGTH_SHORT).show()
            } else {
                val id = database.push().key!!

                val map = HashMap<String, String>()
                map["text"] = text
                map["status"] = "pending"

                database.child(id).setValue(map)

                input.text.clear()
                Toast.makeText(requireContext(), "Submitted", Toast.LENGTH_SHORT).show()
            }
        }

        // Filter Buttons
        pendingBtn.setOnClickListener {
            currentFilter = "pending"
            loadData()
        }

        resolvedBtn.setOnClickListener {
            currentFilter = "resolved"
            loadData()
        }

        // Long click to mark resolved
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val selected = complaintList[position]

            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data in snapshot.children) {
                        val text = data.child("text").value.toString()

                        if (text == selected) {
                            data.ref.child("status").setValue("resolved")
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })

            true
        }

        loadData()

        return view
    }

    private fun loadData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                complaintList.clear()

                for (data in snapshot.children) {
                    val text = data.child("text").value.toString()
                    val status = data.child("status").value.toString()

                    if (status == currentFilter) {
                        complaintList.add(text)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}