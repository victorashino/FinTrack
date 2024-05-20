package com.example.fintrack.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.utils.FinTrackApplication

class MainViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    val all_categories: LiveData<List<Category>> = categoryRepository.allCategories

    companion object {
        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as FinTrackApplication).getDataBase()
            val categoryRepository = CategoryRepository(dataBaseInstance.categoryDao())
            val factory = object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(categoryRepository) as T
                }
            }
            return factory
        }
    }

}