package com.example.businesscard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val biding by lazy {ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
    }
}