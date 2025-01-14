package com.example.android_project.domain.usecase.order

import com.example.android_project.data.source.OrderDao
import com.example.android_project.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetOrdersUseCase(private val orderDao: OrderDao) {
    operator fun invoke (): Flow<List<OrderItem>>{
        return orderDao.getOrders()
            .map{ orders->
                orders.sortedBy { it.orderId }
            }
    }
}