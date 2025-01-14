package com.example.android_project.domain.usecase.client

import com.example.android_project.data.source.ClientDao
import com.example.android_project.domain.model.ClientItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetClientsUseCase(private val clientDao: ClientDao) {
    operator fun invoke (): Flow<List<ClientItem>>{
        return clientDao.getClients()
            .map{ clients->
                clients.sortedBy { it.clientId }
            }
    }
}