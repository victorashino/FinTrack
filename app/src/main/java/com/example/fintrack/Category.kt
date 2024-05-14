package com.example.fintrack

import java.io.Serializable

data class Category (
    val id: Int = 0,
    val name: String,
    val color: String,
    val icon: String
) : Serializable
