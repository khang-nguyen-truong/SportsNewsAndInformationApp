package com.example.sportsnewsandinformationapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsandinformationapp.adapter.News
import com.example.sportsnewsandinformationapp.adapter.NewsAdapter
import com.example.sportsnewsandinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fabAddNews: FloatingActionButton

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?): View? {
                        val view = inflater.inflate(R.layout.fragment_news,
                                                    container,
                                        false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddNews = view.findViewById(R.id.fabAddNews)
        fabAddNews.setOnClickListener {
            showAddNewsDialog()
        }

        // Example data
        val newsList = mutableListOf(
            News(resources.getString(R.string.initialNewsTitle1),
                resources.getString(R.string.initialNewsDesc1),
                resources.getString(R.string.initialNewsImgUrl1)),
            News(resources.getString(R.string.initialNewsTitle2),
                resources.getString(R.string.initialNewsDesc2),
                resources.getString(R.string.initialNewsImgUrl2)),
            News(resources.getString(R.string.initialNewsTitle3),
                resources.getString(R.string.initialNewsDesc3),
                resources.getString(R.string.initialNewsImgUrl3))
        )

        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter

        return view
    }

    private fun showAddNewsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_news, null)

        val editTextImageUrl = dialogView.findViewById<EditText>(R.id.editTextImageUrl)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(resources.getString(R.string.fabAddNewsDesc))
            .setPositiveButton(resources.getString(R.string.dialogPosButAdd)) { dialog, _ ->
                val imageUrl = editTextImageUrl.text.toString().trim()
                val title = editTextTitle.text.toString().trim()
                val description = editTextDescription.text.toString().trim()

                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val newNews = News(title, description, imageUrl)
                    addNews(newNews)
                }

                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.dialogNegButAdd)) { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addNews(newNews: News) {
        newsAdapter.addItem(newNews)
    }
}
