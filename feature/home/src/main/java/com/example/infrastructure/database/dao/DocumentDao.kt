package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.database.entities.DocumentEntity

@Dao
interface DocumentDao {

    @Query("SELECT * FROM document WHERE id LIKE :documentId")
    suspend fun get(documentId: Long): DocumentEntity

    @Query("SELECT * FROM document")
    suspend fun getAllDocument(): List<DocumentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewDocument(vararg user: DocumentEntity)

    @Delete
    suspend fun deleteDocument(item: DocumentEntity)
}
