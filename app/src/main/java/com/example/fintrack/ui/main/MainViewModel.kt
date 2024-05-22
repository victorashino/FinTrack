package com.example.fintrack.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.data.repository.SpentRepository
import com.example.fintrack.utils.FinTrackApplication

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository: CategoryRepository
    val allCategories: LiveData<List<Category>>

    private val spentRepository: SpentRepository
    val allSpents: LiveData<List<Spent>>

    val selectedCategory = MutableLiveData<Category>()

    init {
        val database = (application as FinTrackApplication).dataBase

        val categoryDao = database.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
        allCategories = categoryRepository.allCategories
        selectCategoryAll()

        val spentDao = database.spentDao()
        spentRepository = SpentRepository(spentDao)
        allSpents = spentRepository.allSpent
    }

    fun selectCategory(category: Category) {
        selectedCategory.value = category
    }

    private fun selectCategoryAll() {
        selectedCategory.value = Category(0, "All", "black", R.drawable.ic_add)
    }

    companion object {
        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(application) as T
                }
            }
            return factory
        }
    }
}