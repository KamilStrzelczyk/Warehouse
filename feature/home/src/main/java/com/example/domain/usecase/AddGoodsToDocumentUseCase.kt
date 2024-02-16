package com.example.domain.usecase

import com.example.domain.model.Goods
import com.example.domain.repository.DocumentCrossGoodsRepository
import com.example.domain.repository.GoodsRepository
import javax.inject.Inject

class AddGoodsToDocumentUseCase @Inject constructor(
    private val repository: GoodsRepository,
    private val documentCrossGoods: DocumentCrossGoodsRepository,
) {

    suspend operator fun invoke(
        goods: Goods,
        documentId: Long,
    ) {
        val goodsId = repository.save(goods)
        documentCrossGoods.addGoodsToDocument(
            goodsId = goodsId,
            documentId = documentId,
        )
    }
}
