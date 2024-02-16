package com.example.infrastructure.repository

import com.example.domain.model.Goods
import com.example.domain.repository.GoodsRepository
import com.example.infrastructure.database.dao.GoodsDao
import com.example.infrastructure.mapper.GoodsMapper
import javax.inject.Inject

class GoodsRepositoryImpl @Inject constructor(
    private val goodsDao: GoodsDao,
    private val mapper: GoodsMapper,
) : GoodsRepository {

    override suspend fun delete(goods: Goods) {
        goodsDao.deleteGoods(mapper.toEntityModel(goods))
    }

    override suspend fun get(goodsId: Long): Goods = mapper.toDomainModel(goodsDao.getGoods(goodsId))

    override suspend fun save(goods: Goods) = goodsDao.saveNewGoods(mapper.toEntityModel(goods))

    override suspend fun update(goods: Goods) {
        goodsDao.updateGoods(mapper.toEntityModel(goods))
    }
}
