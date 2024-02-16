package com.example.infrastructure.di

import com.example.domain.repository.ContractorRepository
import com.example.domain.repository.DocumentCrossGoodsRepository
import com.example.domain.repository.DocumentRepository
import com.example.domain.repository.GoodsRepository
import com.example.infrastructure.repository.ContractorRepositoryImpl
import com.example.infrastructure.repository.DocumentCrossGoodsRepositoryImpl
import com.example.infrastructure.repository.DocumentRepositoryImpl
import com.example.infrastructure.repository.GoodsRepositoryImpl
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
    fun bindsDocumentCrossGoodsRepository(impl: DocumentCrossGoodsRepositoryImpl): DocumentCrossGoodsRepository

    @Binds
    fun bindsWorkDocumentRepository(impl: DocumentRepositoryImpl): DocumentRepository

    @Binds
    fun bindsGoodsRepository(impl: GoodsRepositoryImpl): GoodsRepository
}
