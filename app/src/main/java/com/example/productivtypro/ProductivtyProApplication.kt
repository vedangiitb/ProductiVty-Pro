package com.example.productivtypro

import android.app.Application
import com.example.productivtypro.data.AppContainer
import com.example.productivtypro.data.AppDataContainer

class ProductivtyProApplication:Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}