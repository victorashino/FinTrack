package com.example.fintrack.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.SpentItemBinding
import com.example.fintrack.model.Spent

class SpentAdapter :
    ListAdapter<Spent, SpentViewHolder>(SpentAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.spent_item, parent, false)
        return SpentViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object : DiffUtil.ItemCallback<Spent>() {

        override fun areItemsTheSame(oldItem: Spent, newItem: Spent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Spent, newItem: Spent): Boolean {
            return oldItem == newItem
        }
    }
}

class SpentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(spent: Spent) = with(view) {
        val name = findViewById<TextView>(R.id.txtSpentName)
        val value = findViewById<TextView>(R.id.txtSpentValue)
        val icon = findViewById<ImageView>(R.id.imgIcon)
        // TODO(cor)

        name.text = spent.name
        value.text = spent.value.toString()
        icon.setImageResource(spent.category.icon)
        // TODO(cor)
    }
}























