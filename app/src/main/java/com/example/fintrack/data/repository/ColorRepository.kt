package com.example.fintrack.data.repository

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.fintrack.R

class ColorRepository(private val context: Context) {

    fun getColorResourceById(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }

    fun getColor(colorName: String): Int {
        return colorMap[colorName] ?: ContextCompat.getColor(context, R.color.black)
    }

    val colors = arrayOf(
        R.color.black,
        R.color.white,
        R.color.red,
        R.color.violet,
        R.color.ocean_blue,
        R.color.blue,
        R.color.water_blue,
        R.color.water_green,
        R.color.light_green,
        R.color.light_yellow,
        R.color.medium_yellow,
        R.color.light_orange,
        R.color.medium_orange,
        R.color.light_brown,
        R.color.grey,
        R.color.pink,
        R.color.medium_green,
        R.color.medium_brown,
        R.color.white_shadow
    )

    val colorMap = mapOf(
        "black" to R.color.black,
        "white" to R.color.white ,
        "red" to R.color.red,
        "violet" to R.color.violet,
        "cor" to R.color.ocean_blue,
        "ocean_blue" to R.color.blue,
        "water_blue" to R.color.water_blue,
        "water_green" to R.color.water_green,
        "light_green" to R.color.light_green,
        "light_yellow" to R.color.light_yellow,
        "medium_yellow" to R.color.medium_yellow,
        "light_orange" to R.color.light_orange,
        "medium_orange" to R.color.medium_orange,
        "light_brown" to R.color.light_brown,
        "grey" to R.color.grey,
        "pink" to R.color.pink,
        "medium_green" to R.color.medium_green,
        "medium_brown" to R.color.medium_brown,
        "white_shadow" to R.color.white_shadow
    )

}