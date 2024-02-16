package com.example.infrastructure.repository

import com.example.domain.model.Contractor
import com.example.domain.repository.ContractorRepository
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.mapper.ContractorMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContractorRepositoryImpl @Inject constructor(
    private val contractorDao: ContractorDao,
    private val mapper: ContractorMapper,
) : ContractorRepository {

    override suspend fun delete(contractor: Contractor) {
        contractorDao.deleteContractor(mapper.toEntityModel(contractor))
    }

    override suspend fun get(contractorId: Long): Contractor = mapper.toDomainModel(contractorDao.get(contractorId))

    override suspend fun getAll(): Flow<List<Contractor>> = flow {
        contractorDao.getAllContractor().collect {
            emit(mapper.toDomainModel(it))
        }
    }

    override suspend fun save(contractor: Contractor): Long =
        contractorDao.saveNewContractor(mapper.toEntityModel(contractor))

    override suspend fun update(contractor: Contractor) {
        contractorDao.updateContractor(mapper.toEntityModel(contractor))
    }
}
