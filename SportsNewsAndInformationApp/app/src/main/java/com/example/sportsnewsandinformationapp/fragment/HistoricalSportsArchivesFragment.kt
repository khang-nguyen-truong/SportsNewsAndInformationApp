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
import com.example.sportsnewsandinformationapp.adapter.HistoricalSportsArchiveAdapter
import com.example.sportsnewsandinformationapp.adapter.History
import com.example.sportsnewsandinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class HistoricalSportsArchivesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoricalSportsArchiveAdapter
    private lateinit var fabAddHistory: FloatingActionButton
    private var btnSelectDate: Button? = null

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?): View? {
                        val view = inflater.inflate(R.layout.fragment_historical_sports_archives,
                                                    container,
                                        false)

        recyclerView = view.findViewById(R.id.recyclerViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddHistory = view.findViewById(R.id.fabAddHistory)
        fabAddHistory.setOnClickListener {
            showAddHistoryDialog()
        }

        val historyList = mutableListOf(
            History(resources.getString(R.string.initialHistoricalArchivesName1),
                resources.getString(R.string.initialHistoricalArchivesDesc1),
                resources.getString(R.string.initialHistoricalArchivesDate1)),
            History(resources.getString(R.string.initialHistoricalArchivesName2),
                resources.getString(R.string.initialHistoricalArchivesDesc2),
                resources.getString(R.string.initialHistoricalArchivesDate2))
        )

        adapter = HistoricalSportsArchiveAdapter(historyList)
        recyclerView.adapter = adapter

        return view
    }

    private fun showAddHistoryDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_history, null)

        val editTextHistoryName = dialogView.findViewById<EditText>(R.id.editTextHistoryName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)
        btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectDate)

        btnSelectDate?.setOnClickListener {
            showDatePicker()
        }

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(resources.getString(R.string.fabAddHistoricalArchiveDesc))
            .setPositiveButton(resources.getString(R.string.dialogPosButAdd)) { dialog, _ ->
                val historyName = editTextHistoryName.text.toString().trim()
                val description = editTextDescription.text.toString().trim()
                val currentDate = getCurrentDate()

                if (historyName.isNotEmpty()) {
                    val newHistory = History(historyName, description, currentDate)
                    addHistory(newHistory)
                }

                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.dialogNegButAdd)) { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addHistory(newHistory: History) {
        val historiesList = adapter.getEventsList().toMutableList()
        historiesList.add(newHistory)
        adapter.submitList(historiesList)
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
                // Update the button text with selected date
                btnSelectDate?.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }
    }
}