package com.example.android_project.domain.usecase.order

import com.example.android_project.data.source.OrderDao
import com.example.android_project.domain.model.OrderItem
import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class DeleteOrderUseCase(private val orderDao: OrderDao) {
    @Throws(FoodException::class)
    suspend operator fun invoke(orderItem: OrderItem){
        orderDao.deleteOrderItem(orderItem)
    }
}