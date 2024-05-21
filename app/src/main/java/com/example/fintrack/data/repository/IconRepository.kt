package com.example.fintrack.data.repository

import android.content.Context
import android.graphics.drawable.Icon
import androidx.core.content.ContextCompat
import com.example.fintrack.R

class IconRepository(private val context: Context) {

    fun getIconResourceById(iconId: Int): Int {
        return ContextCompat.getDrawable(context, iconId)?.let {
            iconId
        } ?: throw IllegalArgumentException("Invalid icon resource ID")
    }

    val icons = arrayOf(
        R.drawable.ic_home,
        R.drawable.ic_car,
        R.drawable.ic_key,
        R.drawable.ic_game_control,
        R.drawable.ic_wifi,
        R.drawable.ic_clothes,
        R.drawable.ic_credit_card,
        R.drawable.ic_electricity,
        R.drawable.ic_gas_station,
        R.drawable.ic_graphic,
        R.drawable.ic_shopping_cart,
        R.drawable.ic_water_drop,
        R.drawable.ic_add
    )


}