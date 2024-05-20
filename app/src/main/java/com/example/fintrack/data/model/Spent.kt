package com.example.fintrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Spent (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val value: Float,
    val category: Category
) : Serializable


