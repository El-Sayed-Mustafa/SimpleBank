package com.example.simplebank.ui.history.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplebank.ui.home.db.UserDatabase

@Database(entities = [Transaction::class], version = 1)
abstract class TransactionDatabase:RoomDatabase() {

    abstract val transactionDao:TransactionDao


    companion object{
        @Volatile

        private var INSTANCE : TransactionDatabase?=null
        fun getInstance(context: Context): TransactionDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        TransactionDatabase::class.java,
                        "transaction_history"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}