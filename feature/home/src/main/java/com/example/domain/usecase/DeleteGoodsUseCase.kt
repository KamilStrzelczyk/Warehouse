package com.example.domain.usecase

import com.example.domain.model.Goods
import com.example.domain.repository.DocumentCrossGoodsRepository
import com.example.domain.repository.GoodsRepository
import javax.inject.Inject

class DeleteGoodsUseCase @Inject constructor(
    private val goodsRepository: GoodsRepository,
    private val documentCrossGoods: DocumentCrossGoodsRepository,
) {

    suspend operator fun invoke(
        documentId: Long,
        goods: Goods,
    ) {
        goodsRepository.delete(goods)
        documentCrossGoods.delete(
            documentId = documentId,
            goodsId = goods.id,
        )
    }
}
