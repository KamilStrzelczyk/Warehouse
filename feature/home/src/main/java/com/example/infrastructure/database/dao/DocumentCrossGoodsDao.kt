package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.infrastructure.database.entities.DocumentCrossGoodsEntity

@Dao
interface DocumentCrossGoodsDao {

    @Delete
    suspend fun delete(entity: DocumentCrossGoodsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DocumentCrossGoodsEntity)
}
