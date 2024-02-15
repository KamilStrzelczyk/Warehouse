package com.example.infrastructure.mapper

import com.example.domain.model.Contractor
import com.example.domain.model.Document
import com.example.infrastructure.database.entities.DocumentEntity
import javax.inject.Inject

class DocumentMapper @Inject constructor() {

    fun documentsToDomainModel(
        contractors: List<Contractor>,
        documents: List<DocumentEntity>,
    ): List<Document> =
        documents.map { document ->
            document.run {
                Document(
                    id = id,
                    date = date,
                    signature = signature,
                    contractor = setContractor(
                        contractorId = contractorId,
                        contractors = contractors,
                    ),
                    collection = collection,
                )
            }
        }

    fun documentToDomainModel(
        contractors: List<Contractor>,
        document: DocumentEntity,
    ): Document =
        document.run {
            Document(
                id = id,
                date = date,
                signature = signature,
                contractor = setContractor(
                    contractorId = contractorId,
                    contractors = contractors,
                ),
                collection = collection,
            )
        }

    private fun setContractor(
        contractorId: Long,
        contractors: List<Contractor>,
    ) = contractors.first { it.id == contractorId }

    fun toEntityModel(document: Document) = document.run {
        DocumentEntity(
            id = id,
            date = date,
            signature = signature,
            contractorId = contractor.id,
            collection = collection,
        )
    }
}
