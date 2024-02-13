package com.example.infrastructure.repository

import com.example.domain.model.Contractor
import com.example.domain.repository.ContractorRepository
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.mapper.ContractorMapper
import javax.inject.Inject

class ContractorRepositoryImpl @Inject constructor(
    private val contractorDao: ContractorDao,
    private val mapper: ContractorMapper,
) : ContractorRepository {

    override suspend fun getAll(): List<Contractor> =
        mapper.toDomainModel(contractorDao.getAllContractor())

    override suspend fun save(contractor: Contractor) {
        contractorDao.saveNewContractor(mapper.toEntityModel(contractor))
    }

    override suspend fun delete(contractor: Contractor) {
        contractorDao.deleteContractor(mapper.toEntityModel(contractor))
    }
}
