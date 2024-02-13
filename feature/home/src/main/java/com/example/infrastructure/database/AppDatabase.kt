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
                sign = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 1L,
                sign = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 1L,
                sign = "",
                name = "LoremIpsum",
            ),
            ContractorEntity(
                id = 1L,
                sign = "",
                name = "LoremIpsum",
            ),
        )
        getDocumentDao().saveNewDocument(
            DocumentEntity(
                id = 1L,
                date = "20/12/2034",
                sign = "sign",
                contractor = "LoremIpsum",
                collection = "",
            ),
            DocumentEntity(
                id = 2L,
                date = "20/12/2034",
                sign = "sign",
                contractor = "LoremIpsum",
                collection = "",
            ),
            DocumentEntity(
                id = 3L,
                date = "20/12/2034",
                sign = "sign",
                contractor = "LoremIpsum",
                collection = "",
            ),
            DocumentEntity(
                id = 4L,
                date = "20/12/2034",
                sign = "sign",
                contractor = "LoremIpsum",
                collection = "",
            )
        )
    }
}
