package com.example.domain.repository

import com.example.domain.model.Document

interface DocumentRepository {

    suspend fun delete(document: Document)
    suspend fun get(documentId: Long): Document
    suspend fun getAll(): List<Document>
    suspend fun save(document: Document): Long
}
