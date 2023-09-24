package com.example.givinghand.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.givinghand.GivingHandApplication
import com.example.givinghand.data.User
import com.example.givinghand.data.UserDao
import com.example.givinghand.data.UserUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    fun setUsername(username: String?) {
        _username.value = username
    }

    fun getUserIdByUsername(username: String): Int = userDao.getUserIdByUsername(username = username)

    fun getUserByUsername(username: String): Flow<List<User>> = userDao.getUserByUsername(username)

    fun getUserById(id:Int): Flow<List<User>> = userDao.getUser(id)

    fun getAllUsers() = userDao.getAllUsers()

    fun insertUser(user: User) = userDao.insert(user)

     fun updateUser(user: User) = userDao.update(user)

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GivingHandApplication)
                UserViewModel(application.database.UserDao())
            }
        }
    }



    private val _uiState = MutableStateFlow(UserUIState())
    val uiState: StateFlow<UserUIState> = _uiState.asStateFlow()

    fun loginUser(user: User) {
        val previousUser = _uiState.value.user
        updateUser(user, previousUser)
    }

    fun logoutUser(user: User) {
        _uiState.update { currentState ->
            currentState.copy(user = null)
        }
    }


    private fun updateUser(newUser: User, previousUser: User?) {
        _uiState.update { currentState ->
            currentState.copy(
                user = if (newUser is User) newUser else null,

                )
        }
    }

}