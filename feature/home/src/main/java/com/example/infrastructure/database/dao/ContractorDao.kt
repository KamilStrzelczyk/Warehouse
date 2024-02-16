package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.infrastructure.database.entities.ContractorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractorDao {

    @Delete
    suspend fun deleteContractor(item: ContractorEntity)

    @Query("SELECT * FROM contractor WHERE id LIKE :contractorId")
    suspend fun get(contractorId: Long): ContractorEntity

    @Query("SELECT * FROM contractor")
    fun getAllContractor(): Flow<List<ContractorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewContractor(user: ContractorEntity): Long

    @Update
    suspend fun updateContractor(contractor: ContractorEntity)
}
