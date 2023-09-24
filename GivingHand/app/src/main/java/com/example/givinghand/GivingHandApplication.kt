package com.example.givinghand

import android.app.Application
import com.example.givinghand.data.AppDatabase
import com.example.givinghand.datasource.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GivingHandApplication: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
    override fun onCreate() {
        super.onCreate()
        insertData(database)
    }



    private fun insertData(database: AppDatabase) {
        val dataSource = DataSource

        // Run database operations on a background thread
        CoroutineScope(Dispatchers.IO).launch {
            // Insert categories
            dataSource.Categories.forEach{category ->
                database.CategoryDao().insert(category)

            }

            dataSource.ActionItems.forEach{action ->
                database.ActionDao().insert(action)

            }

            dataSource.AuthorizedUsers.forEach{user ->
                database.UserDao().insert(user)

            }

        }
    }
}