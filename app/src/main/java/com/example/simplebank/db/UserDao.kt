package com.example.simplebank.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User):Long

    @Query("SELECT * FROM user_data")
    fun getAllUsers():LiveData<List<User>>

    @Query("SELECT * FROM user_data WHERE name = :name")
    fun getUser(name:String):LiveData<User>


}