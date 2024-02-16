package com.example.domain.model

data class Document(
    val id: Long = 0L,
    val date: String,
    val signature: String,
    val contractor: Contractor?,
    val contractorName: String,
)
