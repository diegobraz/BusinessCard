package com.example.businesscard.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.businesscard.data.dao.Dao
import com.example.businesscard.domain.BusinessCard


@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun businessDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val intance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = intance
                intance
            }
        }
    }
}