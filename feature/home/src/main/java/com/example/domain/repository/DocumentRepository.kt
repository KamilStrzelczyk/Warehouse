package com.example.domain.repository

import com.example.domain.model.Document

interface DocumentRepository {
    suspend fun get(documentId: Long): Document
    suspend fun getAll(): List<Document>
    suspend fun save(document: Document)
    suspend fun delete(document: Document)
}
