package com.example.android_project.classes

import com.example.android_project.domain.model.DrinkItem
import com.example.android_project.domain.model.FoodItem
import java.util.UUID

data class ClientVM(
    val id: UUID = UUID.randomUUID(),  // Automatically generates a unique ID
    var tableId: Int,
    var orderList: OrderVM
){
    fun toEntity(): DrinkItem {
        //val id = if(this.id==-1) null else this.id
        return DrinkItem(
            id = id,
            name = this.name,
            img = this.img,
            price = this.price,
            category = this.category,
            availability = this.availability.toBoolean(),
        )
    }

    companion object {
        fun fromEntity(entity: DrinkItem): ClientVM {
            return ClientVM(
                id=entity.id!!,
                name=entity.name,
                img = entity.img,
                price = entity.price,
                category = entity.category,
                availability = Availability.fromBoolean(entity.availability)
            )
        }
    }
}