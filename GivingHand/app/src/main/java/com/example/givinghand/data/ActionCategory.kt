package com.example.givinghand.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ActionCategory(
    val id: Int,
    var name: String,
    var max_volunteers: Int,
    var address: String,
    var description: String,
    var current_volunteers: Int,
    var date: String,
    val category_id: Int,
    val category_name: String,
    val category_picture: Int
){

}
