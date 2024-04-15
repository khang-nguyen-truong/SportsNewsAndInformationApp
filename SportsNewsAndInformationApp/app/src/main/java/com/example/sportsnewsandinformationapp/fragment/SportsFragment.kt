package com.example.sportsnewsandinformationapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsandinformationapp.R
import com.example.sportsnewsandinformationapp.adapter.Sport
import com.example.sportsnewsandinformationapp.adapter.SportAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SportsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sportAdapter: SportAdapter
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?): View? {
                        val view = inflater.inflate(R.layout.fragment_sports,
                                                    container,
                                        false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        fabAdd = view.findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            showAddSportDialog()
        }

        val sports = mutableListOf(
            Sport(resources.getString(R.string.initialSportType1),
                resources.getString(R.string.initialSportName1),
                resources.getString(R.string.initialSportInstruction1)),
            Sport(resources.getString(R.string.initialSportType2),
                resources.getString(R.string.initialSportName2),
                resources.getString(R.string.initialSportInstruction2)),
            Sport(resources.getString(R.string.initialSportType3),
                resources.getString(R.string.initialSportName3),
                resources.getString(R.string.initialSportInstruction3)),
            Sport(resources.getString(R.string.initialSportType4),
                resources.getString(R.string.initialSportName4),
                resources.getString(R.string.initialSportInstruction4)),
            Sport(resources.getString(R.string.initialSportType5),
                resources.getString(R.string.initialSportName5),
                resources.getString(R.string.initialSportInstruction5))
        )

        sportAdapter = SportAdapter(sports)
        recyclerView.adapter = sportAdapter

        return view
    }

    private fun showAddSportDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_sport, null)

        val sportCategories = resources.getStringArray(R.array.categories_array)
        val spinnerType: Spinner = dialogView.findViewById(R.id.spinnerCategory)
        val arrayAdapter = ArrayAdapter(dialogView.context, android.R.layout.simple_spinner_item, sportCategories)
        spinnerType.adapter = arrayAdapter

        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextInstruction = dialogView.findViewById<EditText>(R.id.editTextInstruction)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(resources.getString(R.string.fabAddSportDesc))
            .setPositiveButton(resources.getString(R.string.dialogPosButAdd)) { dialog, _ ->
                val type = spinnerType.selectedItem.toString()
                val name = editTextName.text.toString().trim()
                val instruction = editTextInstruction.text.toString().trim()

                if (type.isNotEmpty() && name.isNotEmpty() && instruction.isNotEmpty()) {
                    val newSport = Sport(type, name, instruction)
                    addSport(newSport)
                }

                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.dialogNegButAdd)) { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addSport(newSport: Sport) {
        sportAdapter.addItem(newSport)
    }
}