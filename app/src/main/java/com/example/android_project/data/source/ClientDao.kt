package com.example.android_project.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.android_project.domain.model.ClientItem
import com.example.android_project.domain.model.ClientWithOrders
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Query("SELECT * FROM Clients")
    fun getClients():Flow<List<ClientItem>>

    @Query("SELECT * FROM Clients WHERE clientId = :id")
    suspend fun getClientItem(id:Int): ClientItem?

    @Upsert
    suspend fun upsertClientItem(clientItem: ClientItem)

    @Delete
    suspend fun deleteClientItem(clientItem: ClientItem)

    @Transaction
    @Query("SELECT * FROM Clients")
    fun getClientsWithOrders(): List<ClientWithOrders>
}