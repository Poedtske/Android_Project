package com.example.android_project.classes

import com.example.android_project.domain.model.OrderItem
import java.util.UUID

data class OrderVM(
    val id: UUID = UUID.randomUUID(),
    var clientId: UUID,
    var open: Boolean
){
    fun toEntity(): OrderItem {
        //val id = if(this.id==-1) null else this.id
        return OrderItem(
            orderId = id,
            associatedClientId = clientId,
            open = open
        )
    }

    companion object {
        fun fromEntity(entity: OrderItem): OrderVM {
            return OrderVM(
                id=entity.orderId!!,
                clientId =entity.associatedClientId,
                open = entity.open
            )
        }
    }
}