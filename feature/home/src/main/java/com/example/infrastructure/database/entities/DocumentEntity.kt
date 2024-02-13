package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "document")
data class DocumentEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "sign")
    val sign: String,
    @ColumnInfo(name = "contractor")
    val contractor: String,
    @ColumnInfo(name = "collection")
    val collection: String,
)
