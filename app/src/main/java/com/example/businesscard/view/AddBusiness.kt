package com.example.businesscard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityAddBusinessBinding
import com.example.businesscard.databinding.ActivityMainBinding

class AddBusiness : AppCompatActivity() {

    private val biding by lazy { ActivityAddBusinessBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        insertListener()
    }

    private fun insertListener(){
        biding.closeBtn.setOnClickListener {
            finish()
        }

    }
}