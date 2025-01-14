package com.example.android_project.domain.usecase.order

import com.example.android_project.data.source.OrderDao
import com.example.android_project.domain.model.OrderWithFood
import kotlinx.coroutines.flow.Flow

class GetOrdersWithFoodUseCase(private val orderDao: OrderDao) {
    operator fun invoke (): Flow<List<OrderWithFood>>{
        return orderDao.getOrdersWithFood()
    }
}