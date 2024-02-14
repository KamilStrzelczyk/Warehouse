package com.example.infrastructure.di

import com.example.domain.repository.ContractorRepository
import com.example.domain.repository.DocumentRepository
import com.example.infrastructure.repository.ContractorRepositoryImpl
import com.example.infrastructure.repository.DocumentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsContractorRepository(impl: ContractorRepositoryImpl): ContractorRepository

    @Binds
    fun bindsWorkDocumentRepository(impl: DocumentRepositoryImpl): DocumentRepository
}
