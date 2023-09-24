package com.example.givinghand.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id=:id")
    fun getUser(id:Int): Flow<List<User>>

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): Flow<List<User>>

    @Query("SELECT id FROM users WHERE username = :username")
    fun getUserIdByUsername(username: String): Int

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

}


