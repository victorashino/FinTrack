package com.example.fintrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }


    companion object : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.color == newItem.color &&
                    oldItem.icon == newItem.icon
        }
    }
}

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(category: Category) = with(itemView) {

        val name = findViewById<TextView>(R.id.categoryName)
        /*val color = findViewById<TextView>(R.id.text_height)
        val icone = findViewById<TextView>(R.id.text_classification_history)*/

        name.text = "${category.name}"
    }

}