package com.example.simplebank.ui.home.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User):Long

    @Query("SELECT * FROM user_data")
    fun getAllUsers():LiveData<List<User>>

    @Query("SELECT * FROM user_data WHERE name = :name")
    fun getUser(name:String): User


}