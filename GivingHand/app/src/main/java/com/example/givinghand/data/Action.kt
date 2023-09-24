package com.example.givinghand.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "actions", foreignKeys = [(ForeignKey(entity = Category::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("category_id"),
    onDelete = ForeignKey.NO_ACTION))],
    indices = [(Index(value = arrayOf("category_id")))])
data class Action(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name="name") var name: String,
    @NonNull
    @ColumnInfo(name="max_volunteers") var max_volunteers: Int,
    @NonNull
    @ColumnInfo(name="address") var address: String,
    @NonNull
    @ColumnInfo(name="description") var description: String,
    @NonNull
    @ColumnInfo(name="current_volunteers") var current_volunteers: Int,
    @NonNull
    @ColumnInfo(name="date") var date: String,
    @NonNull
    @ColumnInfo(name="category_id") val category_id: Int
) {

}