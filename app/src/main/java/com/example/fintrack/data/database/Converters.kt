package com.example.fintrack.data.database

import androidx.room.TypeConverter
import com.example.fintrack.data.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromCategory(category: Category): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        val type = object : TypeToken<Category>() {}.type
        return Gson().fromJson(category, type)
    }


}