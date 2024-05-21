package com.example.fintrack.data.repository

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.fintrack.R

class ColorRepository(private val context: Context) {

    fun getColorResourceById(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
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

}