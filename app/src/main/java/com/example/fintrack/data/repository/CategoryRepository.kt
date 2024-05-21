package com.example.fintrack.data.repository

import androidx.lifecycle.LiveData
import com.example.fintrack.data.database.CategoryDao
import com.example.fintrack.data.model.Category

class CategoryRepository(private val categoryDao: CategoryDao) {

    val allCategories: LiveData<List<Category>> = categoryDao.getAll()

    suspend fun insert(category: Category) {
        val existingCategory = categoryDao.getCategoryByName(category.name)
        if (existingCategory == null) {
            categoryDao.insert(category)
        }
    }
}