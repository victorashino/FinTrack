package com.example.fintrack.data.database

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FinTrackApplication : Application() {

    companion object {
        private lateinit var instance: FinTrackApplication
        fun getInstance(): FinTrackApplication = instance
    }

    lateinit var dataBase: AppDataBase
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        val prefs = getSharedPreferences("com.example.fintrack", Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            deleteDatabase("fin_track_db")

            prefs.edit().putBoolean("isFirstRun", false).apply()
        }

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "fin_track_db"
        ).fallbackToDestructiveMigration()
            .addCallback(AppDataBaseCallBack())
            .build()
    }

    private class AppDataBaseCallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance.let {
                CoroutineScope(Dispatchers.IO).launch {
                    it.dataBase.categoryDao().insert(
                        Category(
                            0,
                            "All",
                            "black",
                            R.drawable.ic_add
                        )
                    )
                }
            }
        }
    }
}