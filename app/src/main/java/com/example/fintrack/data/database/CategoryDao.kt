package com.example.fintrack.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fintrack.data.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM Category")
    fun getAll(): LiveData<List<Category>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(category: Category)

    @Query("DELETE FROM Category WHERE id = :id")
    fun deleteById(id: Int)

}