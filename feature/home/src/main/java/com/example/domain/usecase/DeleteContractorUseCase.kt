package com.example.domain.usecase

import com.example.domain.model.Contractor
import com.example.domain.repository.ContractorRepository
import javax.inject.Inject

class DeleteContractorUseCase @Inject constructor(
    private val contractorRepository: ContractorRepository,
) {

    suspend operator fun invoke(goods: Contractor) {
        contractorRepository.delete(goods)
    }
}
