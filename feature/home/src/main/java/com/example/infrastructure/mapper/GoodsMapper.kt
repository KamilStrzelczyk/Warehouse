package com.example.infrastructure.mapper

import com.example.domain.model.Goods
import com.example.domain.utils.UnitOfMeasure
import com.example.infrastructure.database.entities.GoodsEntity
import javax.inject.Inject

class GoodsMapper @Inject constructor() {

    fun toDomainModel(goods: GoodsEntity) = goods.run {
        Goods(
            id = goodsId,
            name = name,
            amount = amount,
            unitOfMeasure = UnitOfMeasure.parse(unitOfMeasure),
        )
    }

    fun toEntityModel(goods: Goods) = goods.run {
        GoodsEntity(
            goodsId = goods.id,
            name = name,
            amount = amount,
            unitOfMeasure = unitOfMeasure.name,
        )
    }
}
