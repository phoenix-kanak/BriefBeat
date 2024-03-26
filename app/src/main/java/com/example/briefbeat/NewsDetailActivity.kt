package com.example.briefbeat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        val title = intent.getStringExtra("title")
        val des = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")
        val imageURL = intent.getStringExtra("image")
        val url = intent.getStringExtra("url")
        val titleTV = findViewById<TextView>(R.id.newsTitle)
        val subTitle = findViewById<TextView>(R.id.newsSubtitle)
        val desc = findViewById<TextView>(R.id.newsDes)
        val newsImg = findViewById<ImageView>(R.id.newsImg)
        val newsBtn = findViewById<Button>(R.id.newsButton)
        titleTV.text = title
        subTitle.text = des
        desc.text = content
        Picasso.get().load(imageURL).into(newsImg)
        newsBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }
    }
}