package com.example.simplebank.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebank.db.User
import com.example.simplebank.db.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (private val repo: UserRepo):ViewModel() {

    val users = repo.users

    fun getUser(name: String) = viewModelScope.launch(Dispatchers.IO){
        repo.user(name)
    }

}