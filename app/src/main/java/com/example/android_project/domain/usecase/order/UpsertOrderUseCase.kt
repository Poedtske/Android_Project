package com.example.android_project.domain.usecase.order

import com.example.android_project.data.source.OrderDao
import com.example.android_project.domain.model.OrderItem
import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class UpsertOrderUseCase(private val orderDao: OrderDao) {
    @Throws(FoodException::class)
    suspend operator fun invoke(orderItem: OrderItem){
        /*if(food.name.isEmpty()){
            throw FoodException("Name of food is empty")
        }else if(food.price<=0.0){
            throw FoodException("Price of food is invalid")
        }*/
        orderDao.upsertOrderItem(orderItem)
    }
}