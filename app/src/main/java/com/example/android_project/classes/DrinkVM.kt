package com.example.android_project.classes

import com.example.android_project.domain.model.DrinkItem
import com.example.android_project.domain.model.FoodItem
import java.util.UUID

data class DrinkVM(
    val id: UUID = UUID.randomUUID(),  // Automatically generates a unique ID
    var name: String,
    var img: String,
    var category: DrinkCategory,
    var availability: Availability,
    var price: Double,
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
        fun fromEntity(entity: DrinkItem): DrinkVM {
            return DrinkVM(
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