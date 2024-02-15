package com.example.infrastructure.di

import com.example.domain.provider.LocalDateTimeProvider
import com.example.infrastructure.provider.LocalDateTimeProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ProviderModule {

    @Binds
    fun bindLocalDateTimeProvider(impl: LocalDateTimeProviderImpl): LocalDateTimeProvider
}
