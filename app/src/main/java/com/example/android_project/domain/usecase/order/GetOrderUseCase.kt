package com.example.android_project.domain.usecase.order

import com.example.android_project.api.OrderApiService
import com.example.android_project.classes.OrderVM

class GetOrderUseCase(private val orderApiService: OrderApiService) {
    suspend operator fun invoke(id: Int): OrderVM?{
        return orderApiService.getOrderById(id.toString()).toVM()
    }
}