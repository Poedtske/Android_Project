package com.example.android_project.classes

import com.example.android_project.data.model.FoodEntity
import com.example.android_project.data.model.TableEntity

data class TableVM(
    val id:Int=-1,
    val clients:Set<ClientVM> = emptySet()
) {
    fun toEntity() = TableEntity(
        id = this.id,
        clients = this.clients
    )

    companion object {
        fun placeholder() = TableVM()
    }
}