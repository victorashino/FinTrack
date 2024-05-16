package com.example.fintrack.view.adapter

import com.example.fintrack.model.Spent

sealed class DataItem {

    abstract val id: Long

    data class SpentCardItem(
        val spentCard: Spent,
        override val id: Long = spentCard.id
    ) : DataItem()

}