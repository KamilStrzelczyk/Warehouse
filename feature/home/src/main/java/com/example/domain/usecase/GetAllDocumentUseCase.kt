package com.example.domain.usecase

import com.example.domain.model.Document
import com.example.domain.repository.DocumentRepository
import javax.inject.Inject

class GetAllDocumentUseCase @Inject constructor(private val repository: DocumentRepository) {

    suspend operator fun invoke(): List<Document> = repository.getAll()
}
