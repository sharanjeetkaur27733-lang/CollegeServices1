package com.example.collegeservices

import android.app.DatePickerDialog
import android.os.Bundle
import java.util.Calendar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class LeaveFragment : Fragment() {

    lateinit var reason: EditText
    lateinit var fromDate: EditText
    lateinit var toDate: EditText
    lateinit var submitBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_leave, container, false)

        reason = view.findViewById(R.id.reason)
        fromDate = view.findViewById(R.id.fromDate)
        toDate = view.findViewById(R.id.toDate)
        submitBtn = view.findViewById(R.id.submitLeave)

        fromDate.setOnClickListener {
            showDatePicker(fromDate)
        }

        toDate.setOnClickListener {
            showDatePicker(toDate)
        }

        submitBtn.setOnClickListener {
            val r = reason.text.toString()
            val f = fromDate.text.toString()
            val t = toDate.text.toString()

            if (r.isEmpty() || f.isEmpty() || t.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Leave Submitted", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun showDatePicker(editText: EditText) {
        val cal = Calendar.getInstance()

        val dp = DatePickerDialog(
            requireContext(),
            { _, y, m, d ->
                editText.setText("$d/${m + 1}/$y")
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dp.show()
    }
}