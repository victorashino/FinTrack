package com.example.fintrack.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.model.Spent

class SpentAdapter : ListAdapter<Spent, SpentViewHolder>(SpentAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.spent_item, parent, false)
        return SpentViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        val spent = getItem(position)
        holder.bind(spent)
    }

    companion object : DiffUtil.ItemCallback<Spent>() {

        override fun areItemsTheSame(oldItem: Spent, newItem: Spent): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Spent, newItem: Spent): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.value == newItem.value
        }
    }
}

class SpentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(spent: Spent) = with(itemView) {

        val name = findViewById<TextView>(R.id.txtSpentName)
        val icone = findViewById<ImageView>(R.id.imgIcon)
        val value = findViewById<TextView>(R.id.txtSpentValue)

        name.text = spent.name
        icone.setImageResource(spent.category.icon)
        value.text = spent.value.toString()
    }

}