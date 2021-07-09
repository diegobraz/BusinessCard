package com.example.businesscard.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.businesscard.domain.BusinessCard
import com.example.businesscard.repository.BusinessRepositorie

class MainViewModel(private val businesseCardRepository: BusinessRepositorie):ViewModel() {

    fun insert(businessCard: BusinessCard){
        businesseCardRepository.insert(businessCard)

    }

    fun getAll():LiveData<List<BusinessCard>>{
        return businesseCardRepository.getAll()
    }

}

class MainViewModelFactory(private val repositorie: BusinessRepositorie):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repositorie) as T
        }else{
            throw IllegalArgumentException("UnKnow ViewModel class")
        }
    }

}