package com.example.sportsnewsandinformationapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sportsnewsandinformationapp.R
import com.example.sportsnewsandinformationapp.fragment.AboutMeFragment
import com.example.sportsnewsandinformationapp.fragment.AthletesFragment
import com.example.sportsnewsandinformationapp.fragment.EventsFragment
import com.example.sportsnewsandinformationapp.fragment.HistoricalSportsArchivesFragment
import com.example.sportsnewsandinformationapp.fragment.NewsFragment
import com.example.sportsnewsandinformationapp.fragment.SportsFragment


class PagerAdapter(fm: FragmentManager, private var context: Context) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SportsFragment()
            1 -> NewsFragment()
            2 -> AthletesFragment()
            3 -> EventsFragment()
            4 -> HistoricalSportsArchivesFragment()
            5 -> AboutMeFragment()

            else -> SportsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.resources.getString(R.string.tabSports)
            1 -> context.resources.getString(R.string.tabNews)
            2 -> context.resources.getString(R.string.tabAthletes)
            3 -> context.resources.getString(R.string.tabEvents)
            4 -> context.resources.getString(R.string.tabHistoricalArchives)
            5 -> context.resources.getString(R.string.tabAboutMe)

            else -> context.resources.getString(R.string.tabSports)
        }
    }
}
