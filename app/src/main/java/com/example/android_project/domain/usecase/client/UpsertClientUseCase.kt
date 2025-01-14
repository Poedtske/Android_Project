package com.example.android_project.domain.usecase.client

import com.example.android_project.data.source.ClientDao
import com.example.android_project.domain.model.ClientItem

class UpsertClientUseCase(private val clientDao: ClientDao) {
    //@Throws(FoodException::class)
    suspend operator fun invoke(clientItem: ClientItem){
        /*if(food.name.isEmpty()){
            throw FoodException("Name of food is empty")
        }else if(food.price<=0.0){
            throw FoodException("Price of food is invalid")
        }*/
        clientDao.upsertClientItem(clientItem)
    }
}