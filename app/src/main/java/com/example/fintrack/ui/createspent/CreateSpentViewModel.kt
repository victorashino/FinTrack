package com.example.fintrack.ui.createspent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.data.repository.CategoryRepository
import com.example.fintrack.data.repository.SpentRepository
import com.example.fintrack.ui.createcategory.CreateCategoryViewModel
import com.example.fintrack.utils.FinTrackApplication
import kotlinx.coroutines.launch

class CreateSpentViewModel(private val spentRepository: SpentRepository, application: Application) : AndroidViewModel(application) {

    fun insertIntoDatabase(spent: Spent) {
        viewModelScope.launch {
            spentRepository.insert(spent)
        }
    }

    companion object {
        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as FinTrackApplication).dataBase
            val spentRepository = SpentRepository(dataBaseInstance.spentDao())
            val factory = object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CreateSpentViewModel(spentRepository, application) as T
                }
            }
            return factory
        }
    }
}