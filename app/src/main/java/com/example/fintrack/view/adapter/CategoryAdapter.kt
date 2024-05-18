package com.example.fintrack.view.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableWrapper
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.CategoryItemBinding
import com.example.fintrack.model.Category

class CategoryAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
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

class CategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {

        val background = binding.ctnCategoryItem.background as GradientDrawable

        val name = binding.categoryName
        /*val icone = 0*/

        name.text = "${category.name}"/*
        background.setStroke(2, category.color)
        background.color = ColorStateList.valueOf(category.color)*/
    }

}