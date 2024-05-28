package com.example.fintrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent

@Database(entities = [Category::class, Spent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun spentDao(): SpentDao

}