package com.example.sportsnewsandinformationapp.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsandinformationapp.adapter.Event
import com.example.sportsnewsandinformationapp.adapter.EventsAdapter
import com.example.sportsnewsandinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class EventsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventsAdapter
    private lateinit var fabAddEvent: FloatingActionButton
    private var btnSelectDate: Button? = null

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?): View? {
                        val view = inflater.inflate(R.layout.fragment_events,
                                                    container,
                                        false)

        recyclerView = view.findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddEvent = view.findViewById(R.id.fabAddEvent)
        fabAddEvent.setOnClickListener {
            showAddEventDialog()
        }

        val eventsList = mutableListOf(
            Event(resources.getString(R.string.initialEventName1),
                resources.getString(R.string.initialEventDesc1),
                resources.getString(R.string.initialEventDate1)),
            Event(resources.getString(R.string.initialEventName2),
                resources.getString(R.string.initialEventDesc2),
                resources.getString(R.string.initialEventDate2))
            )

        adapter = EventsAdapter(eventsList)
        recyclerView.adapter = adapter

        return view
    }

    private fun showAddEventDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_event, null)

        val editTextEventName = dialogView.findViewById<EditText>(R.id.editTextEventName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)
        btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectDate)

        btnSelectDate?.setOnClickListener {
            showDatePicker()
        }

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(resources.getString(R.string.fabAddEventDesc))
            .setPositiveButton(resources.getString(R.string.dialogPosButAdd)) { dialog, _ ->
                val eventName = editTextEventName.text.toString().trim()
                val description = editTextDescription.text.toString().trim()
                val currentDate = getCurrentDate()

                if (eventName.isNotEmpty()) {
                    val newEvent = Event(eventName, description, currentDate)
                    addEvent(newEvent)
                }

                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.dialogNegButAdd)) { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addEvent(newEvent: Event) {
        val eventsList = adapter.getEventsList().toMutableList()
        eventsList.add(newEvent)
        adapter.submitList(eventsList)
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$dayOfMonth"
    }

    private fun showDatePicker() {
        btnSelectDate?.let {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                btnSelectDate?.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }
    }
}