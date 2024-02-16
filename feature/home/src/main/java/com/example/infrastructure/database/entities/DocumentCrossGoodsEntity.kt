package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "documentCrossGoods",
    primaryKeys = ["documentId", "goodsId"]
)
data class DocumentCrossGoodsEntity(
    @ColumnInfo(name = "documentId")
    val documentId: Long,
    @ColumnInfo(name = "goodsId")
    val goodsId: Long,
)
