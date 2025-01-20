package com.example.android_project.domain.usecase.order

import com.example.android_project.api.OrderApiService
import com.example.android_project.classes.OrderVM

import com.example.android_project.utils.Exception
import kotlin.jvm.Throws

class DeleteOrderUseCase(private val orderApiService: OrderApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(order: OrderVM){
        orderApiService.deleteOrderById(order.id.toString())
    }
}