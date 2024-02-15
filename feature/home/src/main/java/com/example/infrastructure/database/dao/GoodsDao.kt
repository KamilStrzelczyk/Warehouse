package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.database.entities.GoodsEntity

@Dao
interface GoodsDao {
    @Query("SELECT * FROM goods WHERE id LIKE :goodsId")
    suspend fun getGoods(goodsId: Long): List<GoodsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewGoods(vararg goods: GoodsEntity)

    @Delete
    suspend fun deleteGoods(goods: GoodsEntity)
}
