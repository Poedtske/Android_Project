package com.example.android_project.domain.usecase.order

import com.example.android_project.data.source.OrderDao
import com.example.android_project.domain.model.OrderItem

class GetOrderUseCase(private val orderDao: OrderDao) {
    suspend operator fun invoke(orderId: Int): OrderItem?{
        return orderDao.getOrderItem(orderId)
    }
}