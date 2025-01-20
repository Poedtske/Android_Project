package com.example.android_project.domain.usecase.table

import com.example.android_project.api.TableApiService
import com.example.android_project.classes.TableVM
import com.example.android_project.utils.Exception


class UpsertTableUseCase(private val tableApiService: TableApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(table: TableVM){
        if(table.id==-1){
            throw Exception("Not allowed to create tables")
        }else{
            tableApiService.updateTable(table.toEntity())
        }

    }
}