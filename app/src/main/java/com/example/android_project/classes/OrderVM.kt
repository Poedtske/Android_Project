package com.example.android_project.classes

import com.example.android_project.data.model.FoodEntity
import com.example.android_project.data.model.OrderEntity
import java.util.UUID
import kotlin.random.Random

data class OrderVM(
    val id: Int = -1,
    val client: ClientVM = ClientVM.placeholder(),
    val products: List<FoodVM> = emptyList()
){
    fun toEntity() = OrderEntity(
        id = this.id,
        client = this.client,
        products= this.products
    )

    companion object {
        fun placeholder() = OrderVM()
    }
}