package com.example.android_project.domain.usecase.order

import android.util.Log
import com.example.android_project.api.OrderApiService
import com.example.android_project.classes.OrderVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetOrdersUseCase(private val apiService: OrderApiService) {
    operator fun invoke(): Flow<List<OrderVM>> = flow {
        try {
            val orders = apiService.getOrders()
            emit(orders.map { order->order.toVM() }) // Emit the fetched orders
        } catch (e: Exception) {
            Log.d("OrderListException", e.toString())
            emit(emptyList()) // Emit an empty list on failure
        }
    }
}