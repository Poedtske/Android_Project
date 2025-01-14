package com.example.android_project.domain.usecase.table

import com.example.android_project.data.source.FoodDao
import com.example.android_project.data.source.TableDao
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.domain.model.TableItem

class GetTableUseCase(private val tableDao: TableDao) {
    suspend operator fun invoke(tableId: Int): TableItem?{
        return tableDao.getTableItem(tableId)
    }
}