package com.example.android_project.classes

import com.example.android_project.domain.model.OrderItem
import java.util.UUID
import kotlin.random.Random

data class OrderVM(
    val id: Int = Random.nextInt(),
    var clientId: Int,
){
    fun toEntity(): OrderItem {
        //val id = if(this.id==-1) null else this.id
        return OrderItem(
            orderId = id,
            associatedClientId = clientId
        )
    }

    companion object {
        fun fromEntity(entity: OrderItem): OrderVM {
            return OrderVM(
                id=entity.orderId!!,
                clientId =entity.associatedClientId
            )
        }
    }
}