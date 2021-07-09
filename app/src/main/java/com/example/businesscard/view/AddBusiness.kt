package com.example.businesscard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityAddBusinessBinding
import com.example.businesscard.databinding.ActivityMainBinding
import com.example.businesscard.domain.BusinessCard
import com.example.businesscard.view.adapter.BusinessCardAdapter
import com.example.businesscard.view.viewModel.MainViewModel
import com.example.businesscard.view.viewModel.MainViewModelFactory

class AddBusiness : AppCompatActivity() {

    private val biding by lazy { ActivityAddBusinessBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repositorie)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        insertListener()
    }

    private fun insertListener(){
        biding.closeBtn.setOnClickListener {
            finish()
        }
        biding.btnConfirmar.setOnClickListener {
            val businessCard = BusinessCard(
                nome = biding.txtName?.text.toString(),
                telefone = biding.txtPhone?.text.toString(),
                email= biding.txtEmail?.text.toString(),
                empresa= biding.txtEnterprise.text.toString(),
                fundoPersonalizado= biding.txtColor?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, "Cartão adicionado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}