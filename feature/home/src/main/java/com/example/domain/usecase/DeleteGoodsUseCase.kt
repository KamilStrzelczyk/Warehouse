package com.example.domain.usecase

import com.example.domain.model.Goods
import com.example.domain.repository.DocumentRepository
import com.example.domain.repository.GoodsRepository
import javax.inject.Inject

class DeleteGoodsUseCase @Inject constructor(
    private val goodsRepository: GoodsRepository,
    private val documentRepository: DocumentRepository,
) {

    suspend operator fun invoke(
        documentId: Long,
        goods: Goods,
    ) {
        goodsRepository.delete(goods)
        documentRepository.deleteFromDocumentCrossGoods(
            documentId = documentId,
            goodsId = goods.id,
        )
    }
}
