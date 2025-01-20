package com.example.android_project.domain.usecase.client

import android.util.Log
import com.example.android_project.api.ClientApiService
import com.example.android_project.classes.ClientVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetClientsUseCase(private val apiService: ClientApiService) {
    operator fun invoke(): Flow<List<ClientVM>> = flow {
        try {
            val clients = apiService.getClients()
            val filteredAndSortedClients = clients
                .map { client -> client.toVM() } // Convert to ViewModel
                .filter { clientVM -> !clientVM.paid } // Filter out those who have already paid
                .sortedBy { clientVM -> clientVM.id } // Sort by ID

            emit(filteredAndSortedClients) // Emit the processed list
        } catch (e: Exception) {
            Log.d("ClientListException", e.toString())
            emit(emptyList()) // Emit an empty list on failure
        }
    }
}
