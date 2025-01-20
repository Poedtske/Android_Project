package com.example.android_project.domain.usecase.order

import com.example.android_project.api.OrderApiService
import com.example.android_project.classes.OrderVM
import com.example.android_project.utils.Exception


class UpsertOrderUseCase(private val orderApiService: OrderApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(order: OrderVM){
        if(order.client.id==-1){
            throw Exception("No Client")
        }

        if(order.id==-1){
            orderApiService.createOrder(order.toEntity())
        }else{
            orderApiService.updateOrder(order.toEntity())
        }

    }
}