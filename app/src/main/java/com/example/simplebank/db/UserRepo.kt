package com.example.simplebank.db

class UserRepo(private val userDao: UserDao) {

    val users = userDao.getAllUsers()

    suspend fun insert(user: User){
        userDao.insertUser(user)
    }

}