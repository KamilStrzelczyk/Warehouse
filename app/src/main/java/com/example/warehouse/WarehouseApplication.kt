package com.example.warehouse

import android.app.Application
import com.example.infrastructure.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class WarehouseApplication : Application() {

    @Inject
    lateinit var appDatabase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch {
            appDatabase.initializeDataBase()
        }
    }
}
