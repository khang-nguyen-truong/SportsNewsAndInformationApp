package com.example.sportsnewsandinformationapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sportsnewsandinformationapp.R

class AboutMeFragment : Fragment() {

    private lateinit var imageViewProfile: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewDesc: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?): View? {
                        val view = inflater.inflate(R.layout.fragment_about_me,
                                                    container,
                                        false)

        imageViewProfile = view.findViewById(R.id.imageViewProfile)
        textViewName = view.findViewById(R.id.textViewName)
        textViewDesc = view.findViewById(R.id.textViewDesc)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewProfile.setImageResource(R.drawable.me)
        textViewName.text = resources.getString(R.string.authorName)
        textViewDesc.text = resources.getString(R.string.authorDetails)
    }
}