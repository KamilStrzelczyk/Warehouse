package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.database.entities.ContractorEntity

@Dao
interface ContractorDao {

    @Query("SELECT * FROM contractor")
    suspend fun getAllContractor(): List<ContractorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewContractor(vararg user: ContractorEntity)

    @Delete
    suspend fun deleteContractor(item: ContractorEntity)
}
