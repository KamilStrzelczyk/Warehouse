package com.example.domain.repository

import com.example.domain.model.Goods

interface GoodsRepository {

    suspend fun delete(goods: Goods)
    suspend fun get(goodsId: Long): Goods
    suspend fun save(goods: Goods): Long
    suspend fun update(goods: Goods)
}
