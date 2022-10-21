package com.example.simplebank.ui.history.db

class TransactionRepo (private val transactionDao: TransactionDao){

    val history = transactionDao.getAllTransactions()

    suspend fun insert(transaction: Transaction){
        transactionDao.insertTransaction(transaction)
    }


}