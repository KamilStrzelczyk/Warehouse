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
                    sign = sign,
                    name = name,
                )
            }
        }

    fun toEntityModel(user: Contractor) = user.run {
        ContractorEntity(
            id = id,
            sign = sign,
            name = name,
        )
    }
}
