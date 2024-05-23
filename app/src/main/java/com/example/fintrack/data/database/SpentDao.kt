package com.example.fintrack.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fintrack.data.model.Spent

@Dao
interface SpentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spent: Spent)

    @Query("SELECT SUM(value) FROM spent")
    fun getTotalValue(): LiveData<Float>

    @Update
    suspend fun update(spent: Spent)

    @Query("DELETE FROM Spent WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM Spent WHERE categoryId = :categoryId OR :categoryId = 0")
    fun getSpentByCategoryId(categoryId: Int): LiveData<List<Spent>>

    @Query("DELETE FROM Spent WHERE :selectedCategoryId = :spentCategoryId")
    suspend fun deleteAllByCategory(selectedCategoryId: Int, spentCategoryId: Int)
}