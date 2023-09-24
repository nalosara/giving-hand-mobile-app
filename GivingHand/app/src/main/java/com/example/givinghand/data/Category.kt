package com.example.givinghand.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val id: Int,
    @NonNull
    @ColumnInfo(name="name") val name: String,
    @NonNull
    @ColumnInfo(name="picture") val picture: Int,

    )
