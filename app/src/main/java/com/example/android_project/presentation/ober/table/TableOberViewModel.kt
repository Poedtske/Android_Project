package com.example.android_project.presentation.ober.table

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.ClientVM
import com.example.android_project.classes.TableVM
import com.example.android_project.domain.usecase.client.ClientUseCases
import com.example.android_project.domain.usecase.table.TableUseCases
import com.example.android_project.presentation.components.ClientEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableOberViewModel @Inject constructor(private val tableUseCases: TableUseCases, private val clientUseCases: ClientUseCases, savedStateHandle: SavedStateHandle): ViewModel() {
    private val _table: MutableState<TableVM> = mutableStateOf(TableVM())
    var table: State<TableVM> = _table
    val tableId = savedStateHandle.get<Int>("tableId") ?: -1
    init {
        loadTable(tableId)
    }

    fun loadTable(id:Int) {
        viewModelScope.launch {
            val tableEntity = tableUseCases.getTable(id)
            _table.value = tableEntity!!
        }
    }

    fun onEvent(event: ClientEvent){
        when(event){
            is ClientEvent.Delete -> {

                deleteClient(event.clientVM)
                loadTable(tableId)
            }
        }
    }

    private fun deleteClient(clientVM: ClientVM) {
        viewModelScope.launch {
            clientUseCases.deleteClient(clientVM)
        }

    }
}