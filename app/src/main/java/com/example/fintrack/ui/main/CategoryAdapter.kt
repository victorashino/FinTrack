package com.example.fintrack.ui.main

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.CategoryItemBinding
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.ColorRepository
import kotlin.coroutines.coroutineContext

class CategoryAdapter(
    private val categorys: List<Category>
) : ListAdapter<Category, CategoryViewHolder>(CategoryAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categorys[position]
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

class CategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {

        val name = binding.categoryName
        name.text = "${category.name}"


        val context = binding.root.context
        val color = ContextCompat.getColor(context, category.color)
        val background = binding.ctnCategoryItem.background as GradientDrawable
        background.setColor(color)

    }

}