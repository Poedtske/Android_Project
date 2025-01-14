package com.example.android_project.domain.usecase.table

import com.example.android_project.data.source.TableDao
import com.example.android_project.domain.model.TableItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTablesUseCase(private val tableDao: TableDao) {
    operator fun invoke (): Flow<List<TableItem>>{
        return tableDao.getTables()
            .map{ tables->
                tables.sortedBy{ it.tableId }
            }
    }
}