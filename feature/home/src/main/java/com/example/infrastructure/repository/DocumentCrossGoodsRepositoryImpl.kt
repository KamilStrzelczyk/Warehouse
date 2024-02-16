package com.example.infrastructure.repository

import com.example.domain.model.Goods
import com.example.domain.repository.DocumentCrossGoodsRepository
import com.example.infrastructure.database.dao.DocumentCrossGoodsDao
import com.example.infrastructure.database.dao.GoodsDao
import com.example.infrastructure.database.entities.DocumentCrossGoodsEntity
import com.example.infrastructure.mapper.DocumentCrossGoodsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DocumentCrossGoodsRepositoryImpl @Inject constructor(
    private val documentCrossGoodsDao: DocumentCrossGoodsDao,
    private val goodsDao: GoodsDao,
    private val mapper: DocumentCrossGoodsMapper,
) : DocumentCrossGoodsRepository {

    override suspend fun addGoodsToDocument(goodsId: Long, documentId: Long) {
        documentCrossGoodsDao.insert(
            DocumentCrossGoodsEntity(
                goodsId = goodsId,
                documentId = documentId
            )
        )
    }

    override suspend fun delete(documentId: Long, goodsId: Long) {
        documentCrossGoodsDao.delete(
            mapper.documentWithGoodsToEntityModel(
                documentId = documentId,
                goodsId = goodsId,
            )
        )
    }

    override suspend fun getAllGoodsAsFlow(documentId: Long): Flow<List<Goods>> =
        goodsDao.getByDocumentId(documentId).map {
            mapper.goodsListToDomainModel(it)
        }
}
