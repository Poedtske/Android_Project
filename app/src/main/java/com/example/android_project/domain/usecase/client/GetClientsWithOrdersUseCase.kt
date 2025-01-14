package com.example.android_project.domain.usecase.client

import com.example.android_project.data.source.ClientDao
import com.example.android_project.domain.model.ClientWithOrders
import kotlinx.coroutines.flow.Flow

class GetClientsWithOrdersUseCase(private val clientDao: ClientDao) {
    operator fun invoke(): Flow<List<ClientWithOrders>>?{
        return clientDao.getClientsWithOrders()
    }
}