package com.example.fintrack.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.CategoryItemBinding
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.ColorRepository
import com.example.fintrack.domain.usecase.SelectColorUseCase
import kotlin.coroutines.coroutineContext

class CategoryAdapter(private val onItemClick: (Category) -> Unit) :
    ListAdapter<Category, CategoryViewHolder>(CategoryAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onItemClick)
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

class CategoryViewHolder(
    private val binding: CategoryItemBinding,
    private val onItemClick: (Category) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor", "ResourceType")
    fun bind(category: Category) {

        val context: Context = binding.root.context
        val color = ColorRepository(context).getColor(category.color)

        val name = binding.categoryName
        name.text = category.name
        name.setTextColor(ContextCompat.getColor(context, color))

        val borderColor = ColorRepository(context).getColor(category.color)
        val background = binding.ctnCategoryItem.background as GradientDrawable
        background.setStroke(3, ContextCompat.getColor(context, borderColor))

        binding.root.setOnClickListener {
            onItemClick(category)
        }
    }
}
