package com.example.fintrack.view.adapter

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.SpentItemBinding
import com.example.fintrack.model.Spent

class SpentAdapter :
    ListAdapter<Spent, SpentViewHolder>(SpentAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val binding = SpentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpentViewHolder(binding)
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

class SpentViewHolder(private val binding: SpentItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(spent: Spent) {/*
        val layerDrawable = binding.ctnSpentCard.background as LayerDrawable*/

        val name = binding.txtSpentName
        val value = binding.txtSpentValue
        val icon = binding.imgIcon/*
        val borderColor = layerDrawable.getDrawable(0) as GradientDrawable
        val sideColor = layerDrawable.getDrawable(1) as GradientDrawable*/

        name.text = spent.name
        value.text = "$${spent.value}"
        icon.setImageResource(spent.category.icon)/*
        borderColor.setStroke(1, spent.category.color)
        sideColor.setColor(spent.category.color)*/
    }
}























