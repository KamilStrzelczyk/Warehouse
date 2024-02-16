package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.infrastructure.database.entities.GoodsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoodsDao {
    @Delete
    suspend fun deleteGoods(goods: GoodsEntity)

    @Query("SELECT * FROM goods WHERE goodsId IN (:ids)")
    fun get(ids: List<Long>): Flow<List<GoodsEntity>>

    @Query("SELECT * FROM goods JOIN documentCrossGoods ON goods.goodsId=documentCrossGoods.goodsId WHERE documentId = :documentId")
    fun getByDocumentId(documentId: Long): Flow<List<GoodsEntity>>

    @Query("SELECT * FROM goods WHERE goodsId LIKE :goodsId")
    suspend fun getGoods(goodsId: Long): GoodsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewGoods(goods: GoodsEntity): Long

    @Update
    suspend fun updateGoods(goods: GoodsEntity)
}
