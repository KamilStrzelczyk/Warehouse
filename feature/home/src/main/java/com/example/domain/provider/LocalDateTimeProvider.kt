package com.example.domain.provider

import org.joda.time.LocalDate

interface LocalDateTimeProvider {

    fun getNow(): LocalDate
}
