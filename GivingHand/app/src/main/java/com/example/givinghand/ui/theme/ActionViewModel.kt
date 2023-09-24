package com.example.givinghand.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.givinghand.GivingHandApplication
import com.example.givinghand.data.Action
import com.example.givinghand.data.ActionCategory
import com.example.givinghand.data.ActionDao
import kotlinx.coroutines.flow.Flow

class ActionViewModel (private val actionDao: ActionDao): ViewModel() {

    fun getAllActionsWithCategories(): Flow<List<ActionCategory>> = actionDao.getAllActionsWithCategories()

    fun getActionById(id: Int): Flow<List<Action>> = actionDao.getActionById(id)

    fun getActionByIdWithCategory(id: Int): Flow<List<ActionCategory>> = actionDao.getActionByIdWithCategory(id)

    fun getActionByCategoryWithCategories(category_id: Int): Flow<List<ActionCategory>> = actionDao.getActionsByCategoryIdWithCategories(category_id)

    suspend fun insertAction(action: Action) = actionDao.insert(action)

    suspend fun updateAction(action: Action) = actionDao.update(action)

    suspend fun deleteAction(action: Action) = actionDao.delete(action)


    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GivingHandApplication)
                ActionViewModel(application.database.ActionDao())
            }
        }
    }

}