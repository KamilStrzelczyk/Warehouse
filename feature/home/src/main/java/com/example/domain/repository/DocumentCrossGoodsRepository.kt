package com.example.domain.repository

import com.example.domain.model.Goods
import kotlinx.coroutines.flow.Flow

interface DocumentCrossGoodsRepository {

    suspend fun addGoodsToDocument(goodsId: Long, documentId: Long)
    suspend fun delete(documentId: Long, goodsId: Long)
    suspend fun getAllGoodsAsFlow(documentId: Long): Flow<List<Goods>>
}
