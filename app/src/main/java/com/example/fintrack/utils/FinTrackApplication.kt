package com.example.fintrack.utils

import android.app.Application
import androidx.room.Room
import com.example.fintrack.data.database.AppDataBase

class FinTrackApplication : Application() {

    private lateinit var dataBase: AppDataBase

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "fin_track_db"
        ).build()
    }

    fun getDataBase(): AppDataBase {
        return dataBase
    }
}