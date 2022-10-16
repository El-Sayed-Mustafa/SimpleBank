package com.example.simplebank.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var accountNum: String,
    var balance: String
)