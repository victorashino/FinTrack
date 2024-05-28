package com.example.fintrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val color: String,
    val icon: Int
) : Serializable
