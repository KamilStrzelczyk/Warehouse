package com.example.domain.usecase

import com.example.domain.model.Contractor
import com.example.domain.repository.ContractorRepository
import javax.inject.Inject

class GetAllContractorUseCase @Inject constructor(private val repository: ContractorRepository) {
    suspend operator fun invoke(): List<Contractor> = repository.getAll()
}
