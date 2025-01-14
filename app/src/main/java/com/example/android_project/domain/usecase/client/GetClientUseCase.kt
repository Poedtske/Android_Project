package com.example.android_project.domain.usecase.client

import com.example.android_project.data.source.ClientDao
import com.example.android_project.domain.model.ClientItem

class GetClientUseCase(private val clientDao: ClientDao) {
    suspend operator fun invoke(clientId: Int): ClientItem?{
        return clientDao.getClientItem(clientId)
    }
}