package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contractor")
data class ContractorEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "sign")
    val sign: String,
    @ColumnInfo(name = "name")
    val name: String,
)
