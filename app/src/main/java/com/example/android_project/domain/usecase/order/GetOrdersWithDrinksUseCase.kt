package com.example.android_project.domain.usecase.order

import com.example.android_project.data.source.OrderDao
import com.example.android_project.domain.model.OrderWithDrinks
import kotlinx.coroutines.flow.Flow

class GetOrdersWithDrinksUseCase(private val orderDao: OrderDao) {
    operator fun invoke (): Flow<List<OrderWithDrinks>>{
        return orderDao.getOrdersWithDrinks()
    }
}