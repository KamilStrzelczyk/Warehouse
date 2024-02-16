package com.example.domain.repository

import com.example.domain.model.Contractor
import kotlinx.coroutines.flow.Flow

interface ContractorRepository {

    suspend fun delete(contractor: Contractor)
    suspend fun get(contractorId: Long): Contractor
    suspend fun getAll(): Flow<List<Contractor>>
    suspend fun save(contractor: Contractor): Long
    suspend fun update(contractor: Contractor)
}
