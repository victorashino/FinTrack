package com.example.fintrack.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "spent",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("categoryId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Spent (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val value: Float,
    val categoryId: Int,
    val category: Category
) : Serializable


