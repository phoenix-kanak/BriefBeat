package com.example.briefbeat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsRVAdapter(
    private val articlesArrayList: ArrayList<Articles>,
    private val context: Context
) : RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articles = articlesArrayList[position]
        holder.subtitleTV.text = articles.description
        holder.titleTV.text = articles.title
        Picasso.get().load(articles.urlToImage).into(holder.newsIV)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title", articles.title)
            intent.putExtra("content", articles.content)
            intent.putExtra("description", articles.description)
            intent.putExtra("image", articles.urlToImage)
            intent.putExtra("url", articles.getUrlToImage)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articlesArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView
        val subtitleTV: TextView
        val newsIV: ImageView

        init {
            titleTV = itemView.findViewById(R.id.newsText)
            subtitleTV = itemView.findViewById(R.id.newsDes)
            newsIV = itemView.findViewById(R.id.newsImage)
        }
    }
}
