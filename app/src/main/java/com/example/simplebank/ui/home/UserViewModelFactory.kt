package com.example.simplebank.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplebank.ui.home.db.UserRepo

class UserViewModelFactory(private val repo: UserRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(repo) as T
        else
            throw java.lang.IllegalArgumentException("Unknown viewModel class ")

    }
}

