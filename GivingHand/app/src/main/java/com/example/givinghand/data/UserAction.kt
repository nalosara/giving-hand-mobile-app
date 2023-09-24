package com.example.givinghand.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "user_actions", foreignKeys = [(ForeignKey(entity = Action::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("action_id"),
    onDelete = ForeignKey.NO_ACTION)), (ForeignKey(entity = User::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("user_id"),
    onDelete = ForeignKey.NO_ACTION))],
    indices = [(Index(value = arrayOf("action_id"))), (Index(value = arrayOf("user_id")))])
data class UserAction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name="user_id") val user_id: Int,
    @NonNull
    @ColumnInfo(name="action_id") val action_id: Int
)
