package com.example.givinghand.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserActionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userAction: UserAction)

    @Update
    suspend fun update(userAction: UserAction)

    @Delete
    suspend fun delete(userAction: UserAction)

    @Query("SELECT * FROM user_actions " +
            "WHERE user_id = :user_id")
    fun getAllUserActions(user_id: Int): Flow<List<UserAction>>

    @Query("DELETE FROM user_actions " +
            "WHERE action_id = :action_id")
    fun deleteUserActionsByActonId(action_id: Int)
}