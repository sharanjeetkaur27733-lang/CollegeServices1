package com.example.collegeservices

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import android.content.Intent

class FeesFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var totalFee: TextView
    private lateinit var paidFee: TextView
    private lateinit var dueFee: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var payBtn: Button
    private lateinit var historyBtn: Button
    private lateinit var backArrow: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_fees, container, false)

        // Initialize views
        spinner = view.findViewById(R.id.routeSpinner)
        totalFee = view.findViewById(R.id.totalFee)
        paidFee = view.findViewById(R.id.paidFee)
        dueFee = view.findViewById(R.id.dueFee)
        progressBar = view.findViewById(R.id.progressBar)
        payBtn = view.findViewById(R.id.payBtn)
        historyBtn = view.findViewById(R.id.historyBtn)
        backArrow = view.findViewById(R.id.backArrow)

        // Back button
        backArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Spinner data
        val routes = arrayOf(
            "Select Route",
            "Bus Route A",
            "Bus Route B",
            "Hostel",
            "Course"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            routes
        )
        spinner.adapter = adapter

        // Spinner listener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                when (position) {
                    1 -> updateFees(25000, 15000)
                    2 -> updateFees(30000, 20000)
                    3 -> updateFees(40000, 10000)
                    4 -> updateFees(20000, 5000)
                    else -> clearFees()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Button clicks
        payBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Proceeding to payment", Toast.LENGTH_SHORT).show()
        }

        historyBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Opening history", Toast.LENGTH_SHORT).show()
        }
        payBtn.setOnClickListener {
            val intent = Intent(requireContext(), PaymentActivity::class.java)
            startActivity(intent)
        }
        historyBtn.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun updateFees(total: Int, paid: Int) {
        val due = total - paid

        totalFee.text = "₹ $total"
        paidFee.text = "Paid: ₹ $paid"
        dueFee.text = "Due: ₹ $due"

        val percent = (paid * 100) / total
        progressBar.progress = percent
    }

    private fun clearFees() {
        totalFee.text = "₹ 0"
        paidFee.text = "Paid: ₹ 0"
        dueFee.text = "Due: ₹ 0"
        progressBar.progress = 0
    }
}