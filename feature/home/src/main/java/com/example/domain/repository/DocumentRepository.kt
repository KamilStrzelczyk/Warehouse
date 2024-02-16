package com.example.domain.repository

import com.example.domain.model.Document
import com.example.domain.model.Goods
import kotlinx.coroutines.flow.Flow

interface DocumentRepository {

    suspend fun addToDocument(goodsId: Long, documentId: Long)
    suspend fun delete(document: Document)
    suspend fun deleteFromDocumentCrossGoods(documentId: Long, goodsId: Long)
    suspend fun get(documentId: Long): Document
    suspend fun getAll(): List<Document>
    suspend fun getAllAsFlow(documentId: Long): Flow<List<Goods>>
    suspend fun syncDocumentGoods(documentId: Long)
    suspend fun save(document: Document)
}
