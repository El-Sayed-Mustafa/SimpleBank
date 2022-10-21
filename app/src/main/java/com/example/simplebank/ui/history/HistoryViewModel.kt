package com.example.simplebank.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebank.ui.history.db.Transaction
import com.example.simplebank.ui.history.db.TransactionRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(private val repo: TransactionRepo) : ViewModel() {

    val transactions = repo.history

    fun insertTransaction(transaction: Transaction)=viewModelScope.launch (Dispatchers.IO){
        repo.insert(transaction)
    }

}