package com.example.android_project.domain.usecase.table

data class TablesUseCases(
    val getTables: GetTablesUseCase,
    val getTable: GetTableUseCase,
    val getTablesWithClients: GetTablesWithClientsUseCase,
    val getTableWithClientsUseCase: GetTableWithClientsUseCase
)