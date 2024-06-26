package com.example.fintrack.view.main

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.databinding.SpentItemBinding
import com.example.fintrack.data.model.Spent
import com.example.fintrack.data.repository.ColorRepository

class SpentAdapter(
    private val onItemClicked: (spent: Spent, category: Category) -> Unit,
    private val onItemLongClicked: (spent: Spent) -> Unit
) :
    ListAdapter<Spent, SpentViewHolder>(SpentAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val binding = SpentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        val spent = getItem(position)
        holder.bind(spent, onItemClicked, onItemLongClicked)
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

class SpentViewHolder(private val binding: SpentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(spent: Spent, onItemClicked: (Spent, Category) -> Unit, onItemLongClicked: (Spent) -> Unit) {
        val context = binding.root.context

        val name = binding.txtSpentName
        name.text = spent.name

        val value = binding.txtSpentValue
        value.text = String.format("$%.2f", spent.value)

        val categoryColor = ContextCompat.getColor(context, ColorRepository(context).getColor(spent.category.color))

        val icon = binding.imgIcon
        icon.setImageResource(spent.category.icon)
        icon.setColorFilter(ContextCompat.getColor(icon.context, R.color.white))

        val layerDrawable = binding.ctnSpentCard.background as LayerDrawable

        val borderDrawable = layerDrawable.findDrawableByLayerId(R.id.border) as GradientDrawable
        borderDrawable.setStroke(5, categoryColor)
        val itemSideDrawable =
            layerDrawable.findDrawableByLayerId(R.id.itemSide) as GradientDrawable
        itemSideDrawable.setColor(categoryColor)

        binding.root.setOnLongClickListener {
            true // Retorna verdadeiro para indicar que o clique longo foi tratado
        }

        binding.root.setOnClickListener {
            onItemClicked.invoke(spent, spent.category)
        }

        binding.root.setOnLongClickListener {
            onItemLongClicked(spent)
            true
        }
    }
}
