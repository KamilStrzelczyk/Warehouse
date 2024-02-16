package com.example.domain.usecase

import com.example.domain.provider.LocalDateTimeProvider
import javax.inject.Inject

class GenerateSignatureUseCase @Inject constructor(
    private val provider: LocalDateTimeProvider,
) {

    operator fun invoke(lastIndex: Int): String = "PZ/$lastIndex/${provider.getNow().year}"
}
