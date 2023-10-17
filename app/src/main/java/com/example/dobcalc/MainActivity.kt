package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedDateTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        selectedDateTextView = findViewById(R.id.selectedDateTextView)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)

        val datePickDialog = DatePickerDialog(this, { _, syear, smonth, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${smonth + 1}/$syear"

            selectedDateTextView?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            theDate?.let {
                val selectedDateInMinutes = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMin = currentDate.time / 60000
                    val diffInMinutes = currentDateInMin - selectedDateInMinutes

                    val dateInMinutesTv : TextView = findViewById(R.id.dateInMinutesTv)
                    dateInMinutesTv.text = diffInMinutes.toString()
                }
            }
        }, year, month, day)

        datePickDialog.datePicker.maxDate = System.currentTimeMillis() - (1000 * 60 * 60 * 24)
        datePickDialog.show()
    }
}