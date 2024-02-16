package com.example.domain.usecase

import com.example.domain.repository.DocumentCrossGoodsRepository
import javax.inject.Inject

class GetAllGoodsAsFlowUseCase @Inject constructor(
    private val documentCrossGoods: DocumentCrossGoodsRepository,
) {
    suspend operator fun invoke(documentId: Long) = documentCrossGoods.getAllGoodsAsFlow(documentId)
}
