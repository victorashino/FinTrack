package com.example.fintrack.data.repository

import androidx.lifecycle.LiveData
import com.example.fintrack.data.database.SpentDao
import com.example.fintrack.data.model.Spent

class SpentRepository(private val dao: SpentDao) {

    val totalValue: LiveData<Float> = dao.getTotalValue()

    fun getSpentsByCategoryId(categoryId: Int): LiveData<List<Spent>> {
        return dao.getSpentByCategoryId(categoryId)
    }

    suspend fun insert(spent: Spent) {
        dao.insert(spent)
    }

    suspend fun update(spent: Spent) {
        dao.update(spent)
    }

    suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    suspend fun deleteAllByCategory(categoryId: Int, spentCategoryId: Int) {
        dao.deleteAllByCategory(categoryId, spentCategoryId)
    }

}