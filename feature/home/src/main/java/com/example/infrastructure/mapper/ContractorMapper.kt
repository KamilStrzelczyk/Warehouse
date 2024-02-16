package com.example.infrastructure.mapper

import com.example.domain.model.Contractor
import com.example.infrastructure.database.entities.ContractorEntity
import javax.inject.Inject

class ContractorMapper @Inject constructor() {

    fun toDomainModel(contractors: List<ContractorEntity>): List<Contractor> =
        contractors.map { contractor ->
            contractor.run {
                Contractor(
                    id = id,
                    name = name,
                    signature = signature,
                )
            }
        }

    fun toDomainModel(contractor: ContractorEntity): Contractor =
        contractor.run {
            Contractor(
                id = id,
                name = name,
                signature = signature,
            )
        }

    fun toEntityModel(contractor: Contractor) = contractor.run {
        ContractorEntity(
            id = id,
            name = name,
            signature = signature,
        )
    }
}
