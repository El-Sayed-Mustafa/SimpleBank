package com.example.simplebank.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplebank.ui.history.db.TransactionRepo

class HistoryViewModelFactory (private val repo: TransactionRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java))
            return HistoryViewModel(repo) as T
        else
            throw java.lang.IllegalArgumentException("Unknown viewModel class ")

    }
}