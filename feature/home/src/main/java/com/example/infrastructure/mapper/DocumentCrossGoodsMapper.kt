package com.example.infrastructure.mapper

import com.example.domain.model.Goods
import com.example.domain.utils.UnitOfMeasure
import com.example.infrastructure.database.entities.DocumentCrossGoodsEntity
import com.example.infrastructure.database.entities.GoodsEntity
import javax.inject.Inject

class DocumentCrossGoodsMapper @Inject constructor() {
    fun documentWithGoodsToEntityModel(documentId: Long, goodsId: Long) =
        DocumentCrossGoodsEntity(
            documentId = documentId,
            goodsId = goodsId,
        )

    fun goodsListToDomainModel(goodsList: List<GoodsEntity>): List<Goods> = goodsList.map { goods ->
        goods.run {
            Goods(
                id = goodsId,
                name = name,
                amount = amount,
                unitOfMeasure = UnitOfMeasure.parse(unitOfMeasure),
            )
        }
    }
}
