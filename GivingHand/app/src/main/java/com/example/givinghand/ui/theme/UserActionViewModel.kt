package com.example.givinghand.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.givinghand.GivingHandApplication
import com.example.givinghand.data.Action
import com.example.givinghand.data.UserAction
import com.example.givinghand.data.UserActionDao
import kotlinx.coroutines.flow.Flow

class UserActionViewModel(private val userActionDao: UserActionDao): ViewModel() {

    fun getUserActionsByUserId(user_id: Int): Flow<List<UserAction>> = userActionDao.getAllUserActions(user_id)

    fun DeleteUserActionsByActionId(action_id: Int) = userActionDao.deleteUserActionsByActonId(action_id)

    suspend fun insertUserAction(userAction: UserAction) = userActionDao.insert(userAction)






    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GivingHandApplication)
                UserActionViewModel(application.database.UserActionDao())
            }
        }
    }
}