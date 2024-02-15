package com.example.domain.model

import com.example.domain.utils.UnitOfMeasure

data class Goods(
    val amount: String,
    val name: String,
    val unitOfMeasure: UnitOfMeasure,
)
