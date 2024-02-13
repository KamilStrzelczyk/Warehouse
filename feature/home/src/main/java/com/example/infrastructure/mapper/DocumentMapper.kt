package com.example.infrastructure.mapper

import com.example.domain.model.Document
import com.example.infrastructure.database.entities.DocumentEntity
import javax.inject.Inject

class DocumentMapper @Inject constructor() {

    fun toDomainModel(documents: List<DocumentEntity>): List<Document> =
        documents.map { document ->
            document.run {
                Document(
                    id = id,
                    date = date,
                    sign = sign,
                    contractor = contractor,
                    collection = collection,
                )
            }
        }

    fun toEntityModel(document: Document) = document.run {
        DocumentEntity(
            id = id,
            date = date,
            sign = sign,
            contractor = contractor,
            collection = collection,
        )
    }
}
