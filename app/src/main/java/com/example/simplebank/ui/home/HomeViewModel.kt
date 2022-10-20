package com.example.simplebank.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebank.ui.home.db.User
import com.example.simplebank.ui.home.db.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (private val repo: UserRepo):ViewModel() {


    val users = repo.users

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(user)
    }
    suspend fun getUser(name: String): User {
            return withContext(Dispatchers.IO){
                repo.getUser(name)
            } ?: User(0, "", "", "")
    }

}