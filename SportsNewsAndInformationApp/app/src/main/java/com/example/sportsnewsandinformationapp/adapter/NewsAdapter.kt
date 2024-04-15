package com.example.sportsnewsandinformationapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportsnewsandinformationapp.R

class NewsAdapter(private val newsList: MutableList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageViewNews)
        holder.textTitle.text = currentItem.title
        holder.textDescription.text = currentItem.description
    }

    override fun getItemCount() = newsList.size

    fun addItem(newNews: News) {
        newsList.add(newNews)
        notifyItemInserted(newsList.size - 1)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewNews: ImageView = itemView.findViewById(R.id.imageViewNews)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
    }
}
data class News(
    val title: String,
    val description: String,
    val imageUrl: String
)

