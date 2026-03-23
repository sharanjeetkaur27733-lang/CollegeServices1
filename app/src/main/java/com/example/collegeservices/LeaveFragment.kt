package com.example.collegeservices

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class LeaveFragment : Fragment() {

    private lateinit var reason: EditText
    private lateinit var fromDate: EditText
    private lateinit var toDate: EditText
    private lateinit var submitBtn: Button
    private lateinit var showData: TextView
    private lateinit var backArrow: ImageView

    private var fromCalendar: Calendar? = null
    private var toCalendar: Calendar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_leave, container, false)

        reason = view.findViewById(R.id.reason)
        fromDate = view.findViewById(R.id.fromDate)
        toDate = view.findViewById(R.id.toDate)
        submitBtn = view.findViewById(R.id.submitLeave)
        showData = view.findViewById(R.id.showData)
        backArrow = view.findViewById(R.id.backArrow)

        fromDate.inputType = 0
        toDate.inputType = 0

        backArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        fromDate.setOnClickListener { showDatePicker(true) }
        toDate.setOnClickListener { showDatePicker(false) }

        // Load saved data
        loadData()

        submitBtn.setOnClickListener {

            val r = reason.text.toString().trim()
            val f = fromDate.text.toString().trim()
            val t = toDate.text.toString().trim()

            if (r.isEmpty() || f.isEmpty() || t.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (fromCalendar != null && toCalendar != null) {
                if (fromCalendar!!.after(toCalendar)) {
                    Toast.makeText(requireContext(), "Invalid date range", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Save
            val sharedPref = requireActivity().getSharedPreferences("LeaveData", 0)
            val editor = sharedPref.edit()
            editor.putString("reason", r)
            editor.putString("fromDate", f)
            editor.putString("toDate", t)
            editor.apply()

            // Show on screen
            showData.text = "Reason: $r\nFrom: $f\nTo: $t"

            Toast.makeText(requireContext(), "Leave Submitted ✅", Toast.LENGTH_SHORT).show()

            reason.text.clear()
            fromDate.text.clear()
            toDate.text.clear()
        }

        return view
    }

    private fun loadData() {
        val sharedPref = requireActivity().getSharedPreferences("LeaveData", 0)

        val r = sharedPref.getString("reason", "")
        val f = sharedPref.getString("fromDate", "")
        val t = sharedPref.getString("toDate", "")

        if (!r.isNullOrEmpty()) {
            showData.text = "Reason: $r\nFrom: $f\nTo: $t"
        }
    }

    private fun showDatePicker(isFrom: Boolean) {

        val cal = Calendar.getInstance()

        val dp = DatePickerDialog(
            requireContext(),
            { _, y, m, d ->

                val selectedCal = Calendar.getInstance()
                selectedCal.set(y, m, d)

                val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = format.format(selectedCal.time)

                if (isFrom) {
                    fromCalendar = selectedCal
                    fromDate.setText(date)
                } else {
                    toCalendar = selectedCal
                    toDate.setText(date)
                }
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        dp.show()
    }
}