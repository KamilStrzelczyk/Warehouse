package com.example.infrastructure.repository

import com.example.domain.model.Contractor
import com.example.domain.model.Document
import com.example.domain.model.Goods
import com.example.domain.repository.DocumentRepository
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.database.dao.DocumentCrossGoodsDao
import com.example.infrastructure.database.dao.DocumentDao
import com.example.infrastructure.database.dao.GoodsDao
import com.example.infrastructure.database.entities.DocumentCrossGoodsEntity
import com.example.infrastructure.mapper.ContractorMapper
import com.example.infrastructure.mapper.DocumentMapper
import com.example.infrastructure.mapper.GoodsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentRepositoryImpl @Inject constructor(
    private val documentDao: DocumentDao,
    private val contractorDao: ContractorDao,
    private val documentCrossGoodsDao: DocumentCrossGoodsDao,
    private val goodsDao: GoodsDao,
    private val goodsMapper: GoodsMapper,
    private val documentMapper: DocumentMapper,
    private val contractorMapper: ContractorMapper,
) : DocumentRepository {

    override suspend fun addToDocument(goodsId: Long, documentId: Long) {
        documentCrossGoodsDao.insert(
            DocumentCrossGoodsEntity(
                goodsId = goodsId,
                documentId = documentId
            )
        )
    }

    override suspend fun delete(document: Document) {
        documentDao.deleteDocument(documentMapper.toEntityModel(document))
    }

    override suspend fun deleteFromDocumentCrossGoods(
        documentId: Long,
        goodsId: Long,
    ) {
        documentCrossGoodsDao.delete(
            documentMapper.documentWithGoodsToEntityModel(
                documentId = documentId,
                goodsId = goodsId,
            )
        )
    }

    override suspend fun getAll(): List<Document> =
        documentMapper.documentsToDomainModel(
            contractors = getContractors(),
            documents = documentDao.getAllDocument(),
        )

    override suspend fun getAllAsFlow(documentId: Long): Flow<List<Goods>> {
        val documentGoodsIds = getDocumentGoodsIds(documentId)
        return goodsDao.get(documentGoodsIds.map { it.goodsId }).map {
            it.map { goods -> goodsMapper.toDomainModel(goods) }
        }
    }

    override suspend fun get(documentId: Long): Document {
        return documentMapper.documentToDomainModel(
            contractors = getContractors(),
            document = documentDao.get(documentId)
        )
    }

    private suspend fun getContractors(): List<Contractor> =
        contractorMapper.toDomainModel(contractorDao.getAllContractor())

    override suspend fun syncDocumentGoods(documentId: Long) {
        val documentGoodsIds = getDocumentGoodsIds(documentId)
        goodsDao.get(documentGoodsIds.map { it.goodsId }).first().map(goodsMapper::toDomainModel)
    }

    override suspend fun save(document: Document) {
        documentDao.saveNewDocument(documentMapper.toEntityModel(document))
    }

    private suspend fun getDocumentGoodsIds(documentId: Long): List<DocumentCrossGoodsEntity> =
        documentCrossGoodsDao.getAllFor(documentId)
}
