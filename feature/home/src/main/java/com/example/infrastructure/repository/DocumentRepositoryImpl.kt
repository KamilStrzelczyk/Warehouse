package com.example.infrastructure.repository

import com.example.domain.model.Document
import com.example.domain.repository.DocumentRepository
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.database.dao.DocumentDao
import com.example.infrastructure.mapper.ContractorMapper
import com.example.infrastructure.mapper.DocumentMapper
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val documentDao: DocumentDao,
    private val contractorDao: ContractorDao,
    private val documentMapper: DocumentMapper,
    private val contractorMapper: ContractorMapper,
) : DocumentRepository {

    override suspend fun getAll(): List<Document> =
        contractorDao.getAllContractor().let { contractors ->
            documentMapper.toDomainModel(
                contractors = contractorMapper.toDomainModel(contractors),
                documents = documentDao.getAllDocument()
            )
        }

    override suspend fun save(document: Document) {
        documentDao.saveNewDocument(documentMapper.toEntityModel(document))
    }

    override suspend fun delete(document: Document) {
        documentDao.deleteDocument(documentMapper.toEntityModel(document))
    }
}
