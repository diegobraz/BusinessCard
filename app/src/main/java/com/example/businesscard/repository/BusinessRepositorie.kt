package com.example.businesscard.repository

import com.example.businesscard.domain.BusinessCard
import com.example.businesscard.data.dao.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessRepositorie(private val dao: Dao) {

    fun insert(businessCard: BusinessCard) = runBlocking{
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()

}