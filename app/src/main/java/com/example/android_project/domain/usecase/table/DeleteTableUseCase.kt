package com.example.android_project.domain.usecase.table

import com.example.android_project.api.TableApiService
import com.example.android_project.classes.TableVM

import com.example.android_project.utils.Exception
import kotlin.jvm.Throws

class DeleteTableUseCase(private val tableApiService: TableApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(table: TableVM){
        tableApiService.deleteTableById(table.id.toString())
    }
}