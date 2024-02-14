package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contractor")
data class ContractorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "signature")
    val signature: String,
    @ColumnInfo(name = "name")
    val name: String,
)
