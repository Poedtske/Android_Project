package com.example.android_project.domain.usecase.table

import com.example.android_project.api.TableApiService
import com.example.android_project.classes.TableVM

class GetTableUseCase(private val tableApiService: TableApiService) {
    suspend operator fun invoke(id: Int): TableVM?{
        return tableApiService.getTableById(id.toString()).toVM()
    }
}