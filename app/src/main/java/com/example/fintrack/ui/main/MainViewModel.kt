package com.example.fintrack.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.switchMap
import com.example.fintrack.R
import com.example.fintrack.data.database.SpentDao
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.data.repository.SpentRepository
import com.example.fintrack.utils.FinTrackApplication

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository: CategoryRepository
    val allCategories: LiveData<List<Category>>

    private val spentRepository: SpentRepository
    val selectedCategory = MutableLiveData<Category>()

    val spents: LiveData<List<Spent>>

    private val allCategory = Category(0, "All", "black", R.drawable.ic_add)

    init {
        val database = (application as FinTrackApplication).dataBase


        val categoryDao = database.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
        allCategories = categoryRepository.allCategories
        val spentDao = database.spentDao()
        spentRepository = SpentRepository(spentDao)

        selectCategory(allCategory)

        spents = selectedCategory.switchMap { category ->
            spentRepository.getSpentsByCategoryId(category.id)
        }

    }

    fun selectCategory(category: Category) {
        selectedCategory.value = category
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