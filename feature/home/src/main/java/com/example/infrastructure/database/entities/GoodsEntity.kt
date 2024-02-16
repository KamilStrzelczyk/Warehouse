package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goods")
data class GoodsEntity(
    @PrimaryKey(autoGenerate = true)
    val goodsId: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "amount")
    val amount: Long,
    @ColumnInfo(name = "unitOfMeasure")
    val unitOfMeasure: String,
)
