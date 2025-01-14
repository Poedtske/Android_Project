package com.example.android_project.domain.usecase.client

import com.example.android_project.data.source.ClientDao
import com.example.android_project.domain.model.ClientWithOrders
import kotlinx.coroutines.flow.Flow

class GetClientWithOrdersUseCase(private val clientDao: ClientDao) {
    operator fun invoke(clientId: Int): ClientWithOrders?{
        return clientDao.getClientWithOrders(clientId)
    }
}