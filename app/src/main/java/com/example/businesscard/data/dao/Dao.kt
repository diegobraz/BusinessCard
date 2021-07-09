package com.example.businesscard.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.businesscard.domain.BusinessCard

@Dao
interface Dao {
    @Query("SELECT * FROM BusinessCard")
    fun getAll():LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessCard: BusinessCard)
}