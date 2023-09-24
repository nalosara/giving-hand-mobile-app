package com.example.givinghand.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @NonNull
    @ColumnInfo(name="username") val username: String,
    @NonNull
    @ColumnInfo(name="password") var password: String,
    @NonNull
    @ColumnInfo(name="email") var email: String,
    @NonNull
    @ColumnInfo(name="name") val name: String,
    @NonNull
    @ColumnInfo(name="address") var address: String,
    @NonNull
    @ColumnInfo(name="authorized") val authorized: Int = 0
)

