package com.example.fintrack.ui.createcategory

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fintrack.R
import com.example.fintrack.data.database.AppDataBase
import com.example.fintrack.data.database.CategoryDao
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.data.repository.ColorRepository
import com.example.fintrack.data.repository.IconRepository
import com.example.fintrack.domain.usecase.SelectColorUseCase
import com.example.fintrack.domain.usecase.SelectIconUseCase
import com.example.fintrack.ui.main.MainViewModel
import com.example.fintrack.utils.FinTrackApplication
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