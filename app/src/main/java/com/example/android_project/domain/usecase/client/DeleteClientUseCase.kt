package com.example.android_project.domain.usecase.client

import com.example.android_project.data.source.ClientDao
import com.example.android_project.domain.model.ClientItem
import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class DeleteClientUseCase(private val clientDao: ClientDao) {
    @Throws(FoodException::class)
    suspend operator fun invoke(clientItem: ClientItem){
        clientDao.deleteClientItem(clientItem)
    }
}