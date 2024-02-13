package com.example.domain.repository

import com.example.domain.model.Contractor

interface ContractorRepository {
    suspend fun getAll(): List<Contractor>
    suspend fun save(contractor: Contractor)
    suspend fun delete(contractor: Contractor)
}
