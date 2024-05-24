package com.example.fintrack.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.databinding.CategoryItemBinding
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.ColorRepository

class CategoryAdapter(
    private val onItemClick: (Category) -> Unit,
    private val longClick: (Category) -> Unit) :
    ListAdapter<Category, CategoryViewHolder>(CategoryAdapter) {

    private var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onItemClick, longClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, position == selectedPosition)

        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.bindingAdapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick(category)
        }
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

class CategoryViewHolder(
    private val binding: CategoryItemBinding,
    private val onItemClick: (Category) -> Unit,
    private val longClick: (Category) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor", "ResourceType")
    fun bind(category: Category, isSelected: Boolean) {
        val name = binding.categoryName
        val background = binding.root.background as GradientDrawable
        val context: Context = binding.root.context
        val color = ColorRepository(context).getColor(category.color)

        name.text = category.name
        background.setStroke(5, ContextCompat.getColor(context, color))

        if (isSelected) {
            background.setColor(ContextCompat.getColor(context, color))
            binding.categoryName.setTextColor(ContextCompat.getColor(context, ColorRepository(context).colors[1]))
        } else {
            background.setColor(ContextCompat.getColor(context, ColorRepository(context).colors[1]))
            name.setTextColor(ContextCompat.getColor(context, color))
        }

        binding.root.setOnClickListener {
            onItemClick(category)
        }

        binding.root.setOnLongClickListener {
            longClick(category)
            true
        }
    }
}
