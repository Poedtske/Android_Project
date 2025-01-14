package com.example.android_project.domain.usecase.table

import com.example.android_project.data.source.TableDao
import com.example.android_project.domain.model.TableAndClients
import com.example.android_project.domain.model.TableItem

class GetTablesWithClientsUseCase(private val tableDao: TableDao) {
    suspend operator fun invoke(): List<TableAndClients>{
        return tableDao.getTablesWithClients()
    }
}