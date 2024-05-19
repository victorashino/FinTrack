package com.example.fintrack.ui.main

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.SpentItemBinding
import com.example.fintrack.data.model.Spent
import com.example.fintrack.data.repository.ColorRepository

class SpentAdapter(private val spents: List<Spent>) :
    ListAdapter<Spent, SpentViewHolder>(SpentAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val binding = SpentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        val spent = spents[position]
        holder.bind(spent)
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

    fun bind(spent: Spent) {

        val name = binding.txtSpentName
        name.text = spent.name

        val value = binding.txtSpentValue
        value.text = "$${spent.value}"

        val icon = binding.imgIcon
        icon.setImageResource(spent.category.icon)
        icon.setColorFilter(ContextCompat.getColor(icon.context, ColorRepository(icon.context).colors[1]))

        val context = binding.root.context
        val color = ContextCompat.getColor(context, spent.category.color)
        val layerDrawable = binding.ctnSpentCard.background as LayerDrawable

        val borderDrawable = layerDrawable.findDrawableByLayerId(R.id.border) as GradientDrawable
        borderDrawable.setStroke(2, color)
        val itemSideDrawable = layerDrawable.findDrawableByLayerId(R.id.itemSide) as GradientDrawable
        itemSideDrawable.setColor(color)

    }
}























