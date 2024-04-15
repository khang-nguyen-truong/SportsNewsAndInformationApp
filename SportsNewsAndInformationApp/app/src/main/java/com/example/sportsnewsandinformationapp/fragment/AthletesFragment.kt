package com.example.sportsnewsandinformationapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsandinformationapp.R
import com.example.sportsnewsandinformationapp.adapter.Athlete
import com.example.sportsnewsandinformationapp.adapter.AthletesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AthletesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AthletesAdapter
    private lateinit var fabAddAthlete: FloatingActionButton

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?): View? {
                        val view = inflater.inflate(R.layout.fragment_athletes,
                                                    container,
                                        false)

        recyclerView = view.findViewById(R.id.recyclerViewAthletes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddAthlete = view.findViewById(R.id.fabAddAthlete)
        fabAddAthlete.setOnClickListener {
            showAddAthleteDialog()
        }

        val athletesList = mutableListOf(
            Athlete(resources.getString(R.string.initialAthleteName1),
                    resources.getString(R.string.initialAthleteSport1),
                    resources.getString(R.string.initialAthleteCountry1),
                    resources.getString(R.string.initialAthletePerformance1),
                    resources.getString(R.string.initialAthleteMedal1),
                    resources.getString(R.string.initialAthleteFacts1)
            ),
            Athlete(resources.getString(R.string.initialAthleteName2),
                resources.getString(R.string.initialAthleteSport2),
                resources.getString(R.string.initialAthleteCountry2),
                resources.getString(R.string.initialAthletePerformance2),
                resources.getString(R.string.initialAthleteMedal2),
                resources.getString(R.string.initialAthleteFacts2)
            )
        )

        adapter = AthletesAdapter(athletesList)
        recyclerView.adapter = adapter

        return view
    }

    private fun showAddAthleteDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_athlete, null)

        val editTextAthleteName = dialogView.findViewById<EditText>(R.id.editTextAthleteName)
        val editTextSportName = dialogView.findViewById<EditText>(R.id.editTextSportName)
        val editTextCountry = dialogView.findViewById<EditText>(R.id.editTextCountry)
        val editTextBestPerformance = dialogView.findViewById<EditText>(R.id.editTextBestPerformance)
        val editTextMedal = dialogView.findViewById<EditText>(R.id.editTextMedal)
        val editTextFacts = dialogView.findViewById<EditText>(R.id.editTextFacts)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(resources.getString(R.string.fabAddAthleteDesc))
            .setPositiveButton(resources.getString(R.string.dialogPosButAdd)) { dialog, _ ->
                val athleteName = editTextAthleteName.text.toString().trim()
                val sportName = editTextSportName.text.toString().trim()
                val country = editTextCountry.text.toString().trim()
                val bestPerformance = editTextBestPerformance.text.toString().trim()
                val medal = editTextMedal.text.toString().trim()
                val facts = editTextFacts.text.toString().trim()

                if (athleteName.isNotEmpty() && sportName.isNotEmpty() && country.isNotEmpty() &&
                    bestPerformance.isNotEmpty() && medal.isNotEmpty() && facts.isNotEmpty()) {
                    val newAthlete = Athlete(athleteName, sportName, country, bestPerformance, medal, facts)
                    addAthlete(newAthlete)
                }

                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.dialogNegButAdd)) { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addAthlete(newAthlete: Athlete) {
        val athletesList = adapter.getAthletesList().toMutableList()
        athletesList.add(newAthlete)
        adapter.submitList(athletesList)
    }
}