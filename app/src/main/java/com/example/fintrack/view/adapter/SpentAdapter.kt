package com.example.fintrack.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.fintrack.R
import com.example.fintrack.databinding.AddCategoryItemBinding
import com.example.fintrack.databinding.AddSpentItemBinding
import com.example.fintrack.databinding.SpentItemBinding
import com.example.fintrack.model.AddCategoryCard
import com.example.fintrack.model.AddSpentCard
import com.example.fintrack.model.Spent
import com.example.fintrack.toListOfDataItem
import com.example.fintrack.view.HeaderClickListener

class SpentAdapter :
    ListAdapter<DataItem, SpentAdapter.DataItemViewHolder>(DataItemDiffCallback()) {

    private val ITEM_VIEW_TYPE_ADD_CATEGORY = 0
    private val ITEM_VIEW_TYPE_ADD_SPENT = 1
    private val ITEM_VIEW_TYPE_SPENT = 2

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.AddCategoryCardItem -> ITEM_VIEW_TYPE_ADD_CATEGORY
            is DataItem.AddSpentCardItem -> ITEM_VIEW_TYPE_ADD_SPENT
            is DataItem.SpentCardItem -> ITEM_VIEW_TYPE_SPENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataItemViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ADD_CATEGORY -> DataItemViewHolder.AddCategoryCardViewHolder.from(parent)
            ITEM_VIEW_TYPE_ADD_SPENT -> DataItemViewHolder.AddSpentCardViewHolder.from(parent)
            ITEM_VIEW_TYPE_SPENT -> DataItemViewHolder.SpentViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: DataItemViewHolder, position: Int) {
        when (holder) {
            is DataItemViewHolder.AddCategoryCardViewHolder -> {
                val item = getItem(position) as DataItem.AddCategoryCardItem
                holder.bind(item.addCategoryCard)
            }

            is DataItemViewHolder.AddSpentCardViewHolder -> {
                val item = getItem(position) as DataItem.AddSpentCardItem
                holder.bind(item.addSpentCard)
            }

            is DataItemViewHolder.SpentViewHolder -> {
                val item = getItem(position) as DataItem.SpentCardItem
                holder.bind(item.spentCard)
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<Spent>?) {
        val listDataItem = list?.toListOfDataItem()
        submitList(listDataItem)
    }

    class DataItemDiffCallback : DiffUtil.ItemCallback<DataItem>() {

        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    sealed class DataItemViewHolder(open val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        class AddCategoryCardViewHolder(override val binding: AddCategoryItemBinding) :
            DataItemViewHolder(binding) {

            fun bind(addCategoryCard: AddCategoryCard) {
                val name = binding.txtNewCategory
                val icone = binding.IconNewCategory
                name.text = "New Category"
                icone.setImageResource(R.drawable.ic_add)
            }

            companion object {
                fun from(parent: ViewGroup): DataItemViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = AddCategoryItemBinding.inflate(layoutInflater, parent, false)
                    return AddCategoryCardViewHolder(binding)
                }
            }
        }

        class AddSpentCardViewHolder(override val binding: AddSpentItemBinding) :
            DataItemViewHolder(binding) {

            fun bind(addSpentCard: AddSpentCard) {

                val name = binding.txtNewSpent
                val icone = binding.IconNewSpent
                name.text = "New spent"
                icone.setImageResource(R.drawable.ic_add)
            }

            companion object {
                fun from(parent: ViewGroup): DataItemViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = AddSpentItemBinding.inflate(layoutInflater, parent, false)
                    return AddSpentCardViewHolder(binding)
                }
            }
        }

        class SpentViewHolder(override val binding: SpentItemBinding) :
            DataItemViewHolder(binding) {

            fun bind(spent: Spent) {

                val name = binding.txtSpentName
                val icone = binding.imgIcon
                val value = binding.txtSpentValue
                name.text = spent.name
                icone.setImageResource(spent.category.icon)
                value.text = spent.value.toString()
            }

            companion object {
                fun from(parent: ViewGroup): DataItemViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = SpentItemBinding.inflate(layoutInflater, parent, false)
                    return SpentViewHolder(binding)
                }
            }
        }

    }
}























