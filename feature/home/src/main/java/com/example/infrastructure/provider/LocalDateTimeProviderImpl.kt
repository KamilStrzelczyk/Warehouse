package com.example.infrastructure.provider

import com.example.domain.provider.LocalDateTimeProvider
import org.joda.time.LocalDate
import javax.inject.Inject

internal class LocalDateTimeProviderImpl @Inject constructor() : LocalDateTimeProvider {

    override fun getNow(): LocalDate = LocalDate.now()
}
