package com.example.android_project.classes

import com.example.android_project.data.model.ClientEntity
import com.example.android_project.data.model.FoodEntity
import java.util.UUID
import kotlin.random.Random

data class ClientVM(
    val id: Int = -1,  // Automatically generates a unique ID
    val table: TableVM= TableVM.placeholder(),
    val orders: List<OrderVM> = emptyList(),
    val paid:Boolean= false
){
    fun toEntity() = ClientEntity(
        id = this.id,
        table = this.table,
        orders = this.orders,
        paid = this.paid
    )

    companion object {
        fun placeholder() = ClientVM()
    }
}