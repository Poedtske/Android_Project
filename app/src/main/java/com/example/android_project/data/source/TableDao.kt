package com.example.android_project.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.android_project.domain.model.TableItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {

    @Query("SELECT * FROM Tables")
    fun getTables():Flow<List<TableItem>>

    @Query("SELECT * FROM Tables WHERE tableId = :id")
    suspend fun getTableItem(id:Int): TableItem?

    @Upsert
    suspend fun upsertTableItem(clientItem: TableItem)

    @Delete
    suspend fun deleteTableItem(clientItem: TableItem)

}