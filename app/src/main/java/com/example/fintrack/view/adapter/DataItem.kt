package com.example.fintrack.view.adapter

import com.example.fintrack.model.AddCategoryCard
import com.example.fintrack.model.AddSpentCard
import com.example.fintrack.model.Spent

sealed class DataItem {

    abstract val id: Long

    data class AddCategoryCardItem(
        val addCategoryCard: AddCategoryCard,
        override val id: Long = addCategoryCard.id
    ) : DataItem()

    data class AddSpentCardItem(
        val addSpentCard: AddSpentCard,
        override val id: Long = addSpentCard.id
    ) : DataItem()

    data class SpentCardItem(
        val spentCard: Spent,
        override val id: Long = spentCard.id
    ) : DataItem()

}