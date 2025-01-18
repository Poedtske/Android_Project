package com.example.android_project.presentation.ober

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.ClientVM
import com.example.android_project.classes.FoodVM
import com.example.android_project.domain.usecase.client.ClientsUseCases
import com.example.android_project.domain.usecase.table.TablesUseCases
import com.example.android_project.presentation.components.FoodEvent
import com.example.android_project.presentation.components.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OberTableViewModel @Inject constructor
    (savedStateHandle: SavedStateHandle, private val tableUseCases: TablesUseCases, private val clientsUseCases: ClientsUseCases): ViewModel() {
    private val _clients: MutableState<List<ClientVM>> = mutableStateOf(emptyList())
    var clients: State<List<ClientVM>> =_clients

    var job: Job?=null
    val tableId:Int
    init {
        tableId= savedStateHandle.get<Int>("foodId")?:-1
        loadClients(tableId)
    }

    private fun loadClients(id: Int) {
//        viewModelScope.launch {
//            val tableWithClientsEntity: TableAndClients = tableUseCases.getTableWithClientsUseCase(id)
//            val clients: List<ClientVM> = tableWithClientsEntity.clients.map { ClientVM.fromEntity(it) }
//
//            _clients.value=clients
//        }
    }


    fun onEvent(event: ClientEvent){
        when(event){
            is ClientEvent.Delete -> {
                deleteClient(event.clientVM)
                loadClients(tableId)
            }
        }
    }

    private fun deleteClient(clientVM: ClientVM) {
        viewModelScope.launch {
            clientsUseCases.deleteClient(clientVM.toEntity())
        }

    }
}

sealed class ClientEvent {
    data class Delete(val clientVM: ClientVM):ClientEvent()
}