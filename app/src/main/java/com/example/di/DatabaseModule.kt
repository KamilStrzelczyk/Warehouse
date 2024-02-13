package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.infrastructure.database.AppDatabase
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.database.dao.DocumentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): ContractorDao =
        appDatabase.getContractorDao()

    @Provides
    fun provideAdminDao(appDatabase: AppDatabase): DocumentDao =
        appDatabase.getDocumentDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            context = appContext,
            klass = AppDatabase::class.java,
            name = "Warehouse",
        ).build()
}
