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

    val inputName =MutableLiveData<String>()
    val inputAccountNum = MutableLiveData<String>()
    val inputBalance = MutableLiveData<String>()



    fun save(){

        val name = inputName.value!!
        val accountNum = inputAccountNum.value!!
        val balance = inputBalance.value!!

        insertUser(User(0,name,accountNum,balance))

        inputAccountNum.value = ""
        inputBalance.value=""
        inputName.value=""
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(user)
    }

}