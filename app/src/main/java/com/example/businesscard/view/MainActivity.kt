package com.example.businesscard.view

import Image
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.businesscard.App
import com.example.businesscard.databinding.ActivityMainBinding
import com.example.businesscard.view.adapter.BusinessCardAdapter
import com.example.businesscard.view.viewModel.MainViewModel
import com.example.businesscard.view.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val biding by lazy {ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel:MainViewModel by viewModels{
        MainViewModelFactory((application as App).repositorie)
    }

    private val adapter by lazy {
        BusinessCardAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener(){

        biding.floatingButtonAdd.setOnClickListener {
            val intent = Intent(this,AddBusiness::class.java)
            startActivity(intent)
        }

        adapter.listenershare = {card ->
            Image.share(this,card)
        }

    }

    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this,{businessCard->
            adapter.submitList(businessCard)
        })
    }

}