package com.example.fintrack.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.switchMap
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.data.repository.SpentRepository
import com.example.fintrack.utils.FinTrackApplication

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository: CategoryRepository
    val allCategories: LiveData<List<Category>>
    val selectedCategory = MutableLiveData<Category>()

    private val spentRepository: SpentRepository
    val spents: LiveData<List<Spent>>
    val totalSpentValue: LiveData<Float>
    val totalValueByCategory: LiveData<Float>

    private val allCategory = Category(1, "All", "black", R.drawable.ic_add)

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

        totalSpentValue = spentRepository.totalValue
        totalValueByCategory = valueByCategory(1)
    }

    private fun valueByCategory(categoryId: Int): LiveData<Float> {
        return spentRepository.valueByCategory(categoryId)
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