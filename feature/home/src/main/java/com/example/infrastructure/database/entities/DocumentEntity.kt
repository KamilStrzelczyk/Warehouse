package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "document")
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "signature")
    val signature: String,
    @ColumnInfo(name = "contractor")
    val contractorId: Long,
    @ColumnInfo(name = "collection")
    val collection: String,
)
