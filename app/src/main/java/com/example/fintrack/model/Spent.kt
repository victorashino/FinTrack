package com.example.fintrack.model

import com.example.fintrack.model.Category
import java.io.Serializable

data class Spent (
    val id: Int = 0,
    val name: String,
    val value: Float,
    val category: Category
) : Serializable
