package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.database.entities.DocumentCrossGoodsEntity

@Dao
interface DocumentCrossGoodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DocumentCrossGoodsEntity)

    @Delete
    suspend fun delete(entity: DocumentCrossGoodsEntity)

    @Query("SELECT * FROM DocumentCrossGoods WHERE documentId = :documentId")
    suspend fun getAllFor(documentId: Long): List<DocumentCrossGoodsEntity>
}