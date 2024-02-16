package com.example.domain.model

import com.example.domain.utils.UnitOfMeasure

data class Goods(
    val id: Long = 0L,
    val amount: Long,
    val name: String,
    val unitOfMeasure: UnitOfMeasure,
)
