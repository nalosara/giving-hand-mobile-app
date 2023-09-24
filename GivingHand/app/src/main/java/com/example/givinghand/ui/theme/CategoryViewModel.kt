package com.example.givinghand.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.givinghand.GivingHandApplication
import com.example.givinghand.data.Category
import com.example.givinghand.data.CategoryDao
import kotlinx.coroutines.flow.Flow

class CategoryViewModel(private val categoryDao: CategoryDao): ViewModel() {

    fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()

    fun getCategoryById(id: Int): Flow<List<Category>> = categoryDao.getCategoryById(id)

    fun getCategoryByName(name: String): Flow<List<Category>> = categoryDao.getCategoryByName(name)

    fun getAllCategoryNames(): Flow<List<String>> = categoryDao.getAllCategoriesNames()


    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GivingHandApplication)
                CategoryViewModel(application.database.CategoryDao())
            }
        }
    }

}