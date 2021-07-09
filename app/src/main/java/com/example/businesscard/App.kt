package com.example.businesscard

import android.app.Application
import com.example.businesscard.data.db.AppDataBase
import com.example.businesscard.repository.BusinessRepositorie

class App:Application() {
    val dataBase by lazy { AppDataBase.getDatabase(this) }
    val repositorie by lazy { BusinessRepositorie(dataBase.businessDao()) }
}