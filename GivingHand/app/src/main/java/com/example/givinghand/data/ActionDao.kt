package com.example.givinghand.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {

    @Query("SELECT * FROM actions")
    fun getAllAction(): Flow<List<Action>>
    @Query("SELECT a.id AS id, a.name AS name, a.description AS description, a.address AS address, " +
            "a.current_volunteers AS current_volunteers, a.max_volunteers AS max_volunteers, a.date AS date, " +
            "c.id AS category_id, c.name AS category_name, c.picture AS category_picture " +
            "FROM actions a" +
            " JOIN categories c ON c.id = a.category_id")
    fun getAllActionsWithCategories(): Flow<List<ActionCategory>>

    @Query("SELECT a.id AS id, a.name AS name, a.description AS description, a.address AS address, " +
            "a.current_volunteers AS current_volunteers, a.max_volunteers AS max_volunteers, a.date AS date, " +
            "c.id AS category_id, c.name AS category_name, c.picture AS category_picture " +
            "FROM actions a" +
            " JOIN categories c ON c.id = a.category_id " +
            "WHERE a.category_id=:category_id")
    fun getActionsByCategoryIdWithCategories(category_id:Int): Flow<List<ActionCategory>>

    @Query("SELECT * FROM actions WHERE category_id=:category_id")
    fun getActionsByCategoryId(category_id:Int): Flow<List<Action>>

    @Query("SELECT * FROM actions WHERE id=:id")
    fun getActionById(id:Int): Flow<List<Action>>

    @Query("SELECT a.id AS id, a.name AS name, a.description AS description, a.address AS address, " +
            "a.current_volunteers AS current_volunteers, a.max_volunteers AS max_volunteers, a.date AS date, " +
            "c.id AS category_id, c.name AS category_name, c.picture AS category_picture " +
            "FROM actions a" +
            " JOIN categories c ON c.id = a.category_id " +
            "WHERE a.id=:id")
    fun getActionByIdWithCategory(id:Int): Flow<List<ActionCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(action: Action)

    @Update
    suspend fun update(action: Action)

    @Delete
    suspend fun delete(action: Action)
}