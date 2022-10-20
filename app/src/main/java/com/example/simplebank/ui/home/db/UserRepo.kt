package com.example.simplebank.ui.home.db

import androidx.lifecycle.LiveData

class UserRepo(private val userDao: UserDao) {

    val users = userDao.getAllUsers()

    suspend fun insert(user: User){
        userDao.insertUser(user)
    }

    fun getUser(name: String): User {
        return userDao.getUser(name)
    }




}