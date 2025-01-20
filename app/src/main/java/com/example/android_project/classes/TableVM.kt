package com.example.android_project.classes

import com.example.android_project.data.model.TableEntity

data class TableVM(
    val id:Int=-1,
    val clients:List<ClientVM> = emptyList()
) {
    fun toEntity() = TableEntity(
        id = this.id,
        clients = this.clients
    )

    companion object {
        fun placeholder() = TableVM()
    }
}