package com.example.infrastructure.repository

import com.example.domain.model.Document
import com.example.domain.repository.DocumentRepository
import com.example.infrastructure.database.dao.DocumentDao
import com.example.infrastructure.mapper.DocumentMapper
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val documentDao: DocumentDao,
    private val mapper: DocumentMapper,
) : DocumentRepository {

    override suspend fun getAll(): List<Document> =
        mapper.toDomainModel(documentDao.getAllDocument())

    override suspend fun save(document: Document) {
        documentDao.saveNewDocument(mapper.toEntityModel(document))
    }

    override suspend fun delete(document: Document) {
        documentDao.deleteDocument(mapper.toEntityModel(document))
    }
}
