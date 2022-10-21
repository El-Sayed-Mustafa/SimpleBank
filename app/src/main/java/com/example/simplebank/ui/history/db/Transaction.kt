package com.example.simplebank.ui.history.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var sender: String,
    var receiver: String,
    var amount: String,
    var currentTime: String,
)