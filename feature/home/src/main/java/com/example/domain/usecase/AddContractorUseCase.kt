package com.example.domain.usecase

import com.example.domain.model.Contractor
import com.example.domain.repository.ContractorRepository
import javax.inject.Inject

class AddContractorUseCase @Inject constructor(private val repository: ContractorRepository) {
    suspend operator fun invoke(
        id: Long,
        sign: String,
        name: String,
    ) {
        repository.save(
            Contractor(
                id = id,
                sign = sign,
                name = name,
            )
        )
    }
}
