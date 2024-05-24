package com.example.fintrack.view.createcategory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.data.database.FinTrackApplication
import kotlinx.coroutines.launch

class CreateCategoryViewModel(private val categoryRepository: CategoryRepository, application: Application) : AndroidViewModel(application) {

    fun insertIntoDatabase(category: Category) {
        viewModelScope.launch {
            categoryRepository.insert(category)
        }
    }

    companion object {
        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as FinTrackApplication).dataBase
            val categoryRepository = CategoryRepository(dataBaseInstance.categoryDao())
            val factory = object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CreateCategoryViewModel(categoryRepository, application) as T
                }
            }
            return factory
        }
    }

}