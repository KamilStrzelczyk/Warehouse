package com.example.presentation.component

import com.example.domain.model.Contractor
import com.example.domain.model.Document
import com.example.domain.model.Goods
import com.example.domain.utils.UnitOfMeasure

val fakeContractor = Contractor(
    id = 1L,
    signature = "",
    name = "LoremIpsum",
)

val fakeDocument = Document(
    id = 1L,
    date = "12/12/2034",
    signature = "LoremIpsum",
    contractor = fakeContractor,
    collection = "",
)

val fakeGoods = Goods(
    amount = "20",
    name = "LoremIpsum",
    unitOfMeasure = UnitOfMeasure.Kg,
)
