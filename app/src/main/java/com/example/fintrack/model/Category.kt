package com.example.fintrack.model

import java.io.Serializable

data class Category (
    val id: Int = 0,
    val name: String,
    val color: Int,
    val icon: Int
) : Serializable
