package com.example.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.utils.UnitOfMeasure
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.database.dao.DocumentCrossGoodsDao
import com.example.infrastructure.database.dao.DocumentDao
import com.example.infrastructure.database.dao.GoodsDao
import com.example.infrastructure.database.entities.ContractorEntity
import com.example.infrastructure.database.entities.DocumentCrossGoodsEntity
import com.example.infrastructure.database.entities.DocumentEntity
import com.example.infrastructure.database.entities.GoodsEntity

@Database(
    entities = [
        ContractorEntity::class,
        DocumentEntity::class,
        GoodsEntity::class,
        DocumentCrossGoodsEntity::class,
    ],
    version = 1,
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getDocumentDao(): DocumentDao
    abstract fun getContractorDao(): ContractorDao
    abstract fun getGoodsDao(): GoodsDao
    abstract fun getDocumentCrossGoodsDao(): DocumentCrossGoodsDao

    suspend fun initializeDataBase() {
        getContractorDao().saveNewContractor(
            ContractorEntity(
                id = 1L,
                signature = "",
                name = "SuperMarket",
            )
        )
        getContractorDao().saveNewContractor(
            ContractorEntity(
                id = 2L,
                signature = "",
                name = "Hipermarket",
            ),
        )
        getDocumentDao().saveNewDocument(
            DocumentEntity(
                documentId = 2L,
                date = "20/12/2034",
                signature = "signature",
                contractorId = 1L,
                contractorName = "Hipermarket",
            ),
        )
        getGoodsDao().saveNewGoods(
            GoodsEntity(
                goodsId = 1L,
                name = "Mandarynka",
                amount = 10,
                unitOfMeasure = UnitOfMeasure.Kg.name,
            )
        )
        getDocumentCrossGoodsDao().insert(
            DocumentCrossGoodsEntity(
                documentId = 2L,
                goodsId = 1L,
            )
        )
    }
}
