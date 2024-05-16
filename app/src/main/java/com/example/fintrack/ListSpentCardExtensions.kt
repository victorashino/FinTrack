package com.example.fintrack

import com.example.fintrack.model.AddCategoryCard
import com.example.fintrack.model.Spent
import com.example.fintrack.view.adapter.DataItem

fun List<Spent>.toListOfDataItem(): List<DataItem> {
    val grouping = this.groupBy { spent ->
        spent.id
    }

    val listDataItem = mutableListOf<DataItem>()
    grouping.forEach { mapEntry ->
        listDataItem.add(DataItem.AddCategoryCardItem(
            AddCategoryCard(
                0,
                "",
                R.drawable.ic_add
            )
        ))
        listDataItem.addAll(
            mapEntry.value.map { spent ->
                DataItem.SpentCardItem(spent)
            }
        )
    }
    return listDataItem
}
