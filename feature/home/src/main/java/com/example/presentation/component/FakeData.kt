package com.example.presentation.component

import com.example.domain.model.Contractor
import com.example.domain.model.Document

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
