package com.example.domain.usecase

import com.example.domain.model.Goods
import com.example.domain.repository.GoodsRepository
import javax.inject.Inject

class GetGoodsUseCase @Inject constructor(private val repository: GoodsRepository) {

    suspend operator fun invoke(goodsId: Long): Goods = repository.get(goodsId)
}
