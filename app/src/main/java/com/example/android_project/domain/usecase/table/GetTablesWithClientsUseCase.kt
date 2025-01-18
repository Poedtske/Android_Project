package com.example.android_project.domain.usecase.table

import com.example.android_project.data.source.TableDao
import com.example.android_project.domain.model.TableItem
import kotlinx.coroutines.flow.Flow

class GetTablesWithClientsUseCase(private val tableDao: TableDao) {
//    operator fun invoke(): Flow<List<TableAndClients>> {
//        return tableDao.getTablesWithClients()
//    }
}