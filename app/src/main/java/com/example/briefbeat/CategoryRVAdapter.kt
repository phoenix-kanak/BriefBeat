package com.example.briefbeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CategoryRVAdapter(
    private val categoryModal: List<CategoryModal>,
    private val categoryClickInterface: CategoryClickInterface,
    kFunction1: (Int) -> Unit
) : RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoryModal1 = categoryModal[position]
        holder.categoryTV.text = categoryModal1.category
        Picasso.get().load(categoryModal1.categoryImageURL).into(holder.categoryIV)
        holder.itemView.setOnClickListener { categoryClickInterface.onCategoryClick(position) }
    }

    override fun getItemCount(): Int {
        return categoryModal.size
    }

    interface CategoryClickInterface {
        fun onCategoryClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTV: TextView
        val categoryIV: ImageView

        init {
            categoryIV = itemView.findViewById(R.id.catImage)
            categoryTV = itemView.findViewById(R.id.catText)
        }
    }
}
