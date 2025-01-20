package com.example.android_project.domain.usecase.table

data class TableUseCases (
    val getTables: GetTablesUseCase,
    val getTable: GetTableUseCase,
    val upsertTable: UpsertTableUseCase,
    val deleteTable: DeleteTableUseCase
)