package com.example.android_project.domain.usecase.table

import android.util.Log
import com.example.android_project.api.TableApiService
import com.example.android_project.classes.TableVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTablesUseCase(private val apiService: TableApiService) {
    operator fun invoke(): Flow<List<TableVM>> = flow {
        try {
            val tables = apiService.getTables()
            emit(tables.map { table->table.toVM() }) // Emit the fetched tables
        } catch (e: Exception) {
            Log.d("TableListException", e.toString())
            emit(emptyList()) // Emit an empty list on failure
        }
    }
}