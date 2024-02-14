package com.example.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infrastructure.database.dao.ContractorDao
import com.example.infrastructure.database.dao.DocumentDao
import com.example.infrastructure.database.entities.ContractorEntity
import com.example.infrastructure.database.entities.DocumentEntity

@Database(
    entities = [
        ContractorEntity::class,
        DocumentEntity::class,
    ],
    version = 1,
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getDocumentDao(): DocumentDao
    abstract fun getContractorDao(): ContractorDao

    suspend fun initializeDataBase() {
        getContractorDao().saveNewContractor(
            ContractorEntity(
                id = 1L,
                signature = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 2L,
                signature = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 3L,
                signature = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 4L,
                signature = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 5L,
                signature = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 6L,
                signature = "",
                name = "LoremIpsum",
            ),
        )
        getDocumentDao().saveNewDocument(
            DocumentEntity(
                id = 2L,
                date = "20/12/2034",
                signature = "signature",
                contractorId = 1L,
                collection = "",
            ),
            DocumentEntity(
                id = 2L,
                date = "20/12/2034",
                signature = "signature",
                contractorId = 1L,
                collection = "",
            ),
            DocumentEntity(
                id = 3L,
                date = "20/12/2034",
                signature = "signature",
                contractorId = 1L,
                collection = "",
            ),
            DocumentEntity(
                id = 4L,
                date = "20/12/2034",
                signature = "signature",
                contractorId = 1L,
                collection = "",
            )
        )
    }
}
