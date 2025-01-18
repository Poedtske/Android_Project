package com.example.android_project.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.android_project.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM Orders")
    fun getOrders():Flow<List<OrderItem>>

    @Query("SELECT * FROM Orders WHERE orderId = :id")
    suspend fun getOrderItem(id:Int): OrderItem?

    @Upsert
    suspend fun upsertOrderItem(orderItem: OrderItem)

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)

}