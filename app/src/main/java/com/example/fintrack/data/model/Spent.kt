package com.example.fintrack.data.model

import java.io.Serializable

data class Spent (
    val id: Long = 0,
    val name: String,
    val value: Float,
    val category: Category
) : Serializable


